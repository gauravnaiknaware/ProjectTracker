package com.bonzai.controllers;

import com.bonzai.model.Campaign;
import com.bonzai.model.Comment;
import com.bonzai.model.User;
import com.bonzai.model.UserTime;
import com.bonzai.repository.CampaignRepository;
import com.bonzai.repository.CommentRepository;
import com.bonzai.repository.UserRepository;
import com.bonzai.repository.UserTimeRepository;
import com.bonzai.services.CampaignService;
import com.bonzai.services.UserService;
import com.bonzai.services.UserTimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/console")
public class MainController {

	final static Logger log = Logger.getLogger(MainController.class);
	
	@Value("${admin.statusnotlike}")
	private String status_not_like;
	
	@Value("${create.teams}")
	private String teams;
	
	@Value("${create.statuses}")
	private String statuses;
	
	@Value("${create.zones}")
	private String zones;
	
	@Value("${create.priorities}")
	private String priorities;
	
	@Value("${create.tasktypes}")
	private String task_types;
	
	@Value("${create.build}")
	private String builds;
	
	@Value("${create.roles}")
	private String roles;
	
	private final static String START = "start";
	private final static String STOP = "stop";
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	CampaignRepository campaignRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTimeService userTimeService;

	@Autowired
	UserTimeRepository userTimeRepository;

	@Autowired
	CommentRepository commentRepository;
	
	@RequestMapping(value="/dashboard")
	public String dashboard(Model model,HttpSession session){
		List<Campaign> campaigns = campaignRepository.findByStatusNotLike(status_not_like);
		model.addAttribute("campaigns", campaigns);
		return "dashboard";
	}
	
	@RequestMapping(value="/create")
	public String createTicket(Model model,HttpSession session){
		
		List<String> team = Arrays.asList(teams.split("\\s*,\\s*"));
		List<String> status = Arrays.asList(statuses.split("\\s*,\\s*"));
		List<String> zone = Arrays.asList(zones.split("\\s*,\\s*"));
		List<String> priority = Arrays.asList(priorities.split("\\s*,\\s*"));
		List<String> task = Arrays.asList(task_types.split("\\s*,\\s*"));
		model.addAttribute("team", team);
		model.addAttribute("status", status);
		model.addAttribute("zone", zone);
		model.addAttribute("priority", priority);
		model.addAttribute("task", task);
		return "createticket";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteTicket(@PathVariable("id") long id){
		campaignService.delete(id);
		return "redirect:/console/dashboard";
	}
	
	@RequestMapping(value="/view/{id}")
	public String viewTicket(@PathVariable("id") long id,Model model,HttpSession session){
		
		Campaign campaign = campaignService.getById(id);
		model.addAttribute("campaign", campaign);

		List<Comment> comments = commentRepository.findByCampaignIdOrderByIdDesc(id);
		model.addAttribute("comments",comments);

		List<String> team = Arrays.asList(teams.split("\\s*,\\s*"));
		List<String> status = Arrays.asList(statuses.split("\\s*,\\s*"));
		List<String> zone = Arrays.asList(zones.split("\\s*,\\s*"));
		List<String> priority = Arrays.asList(priorities.split("\\s*,\\s*"));
		List<String> task = Arrays.asList(task_types.split("\\s*,\\s*"));
		List<String> build = Arrays.asList(builds.split("\\s*,\\s*"));
		model.addAttribute("team", team);
		model.addAttribute("status", status);
		model.addAttribute("zone", zone);
		model.addAttribute("priority", priority);
		model.addAttribute("task", task);
		model.addAttribute("build", build);
		
		return "viewticket";
	}
	
	@RequestMapping(value="/adduser")
	public String addUser(Model model){
		List<String> team = Arrays.asList(teams.split("\\s*,\\s*"));
		List<String> role = Arrays.asList(roles.split("\\s*,\\s*"));
		model.addAttribute("team", team);
		model.addAttribute("role", role);
		
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		
		return "adduser";
	}
	
	@RequestMapping("/ticket")
	public String userTicket(HttpSession session, Model model) {
		List<Campaign> campaigns = campaignRepository.findByUserIdAndStatusNotLike((Long)session.getAttribute("userId"),status_not_like);
		model.addAttribute("campaigns", campaigns);
		return "ticket";
	}

	@RequestMapping("/assign/{id}")
	public String assignMe(HttpSession session, Model model,@PathVariable("id") long id) {
		Campaign campaign = campaignService.getById(id);
		User user = userService.getById ((Long)session.getAttribute("userId"));
		campaign.setUser(user);
		campaign.setTeam(user.getTeam());
		campaignService.save(campaign);
		return "redirect:/console/ticket";
	}

	@RequestMapping("/assign/{id}/{team}")
	public String assignTo(HttpSession session,
						   Model model,
						   @PathVariable("id") long id,
						   @PathVariable("team")String team){
		Campaign campaign = campaignService.getById(id);
		campaign.setUser(null);
		campaign.setTeam(team);
		campaignService.save(campaign);
		return "redirect:/console/ticket";
	}
	
	@RequestMapping("/stop/{id}")
	public String stopState(@PathVariable("id") long id) {
		Campaign campaign = campaignService.getById(id);
		campaign.setState(STOP);
		campaignService.save(campaign);

		UserTime userTime = userTimeRepository.findTopByCampaignIdAndEndTimeIsNullOrderByIdDesc(id);
		userTime.setEndTime(new Date());
		userTimeService.save(userTime);
		return "redirect:/console/ticket";
	}
	
	@RequestMapping("/start/{id}")
	public String startState(HttpSession session,@PathVariable("id") long id) {
		List<Campaign> list = campaignRepository.findByUserIdAndStatusNotLikeAndStateLike((Long)session.getAttribute("userId"),status_not_like, START);
		if (!list.isEmpty()) {
			for (Campaign c : list) {
				if (c.getState().equals(START)) {
					c.setState(STOP);
					campaignService.save(c);
				}
			}
		}
		Campaign campaign = campaignService.getById(id);
		campaign.setState(START);
		campaignService.save(campaign);

		UserTime userTime = new UserTime();
		userTime.setCampaign(campaign);
		userTime.setUser(campaign.getUser());
		userTime.setStartTime(new Date());
		userTimeService.save(userTime);

		return "redirect:/console/ticket";
	}

	@RequestMapping("/timespent/{id}")
    public String timespent(Model model,@PathVariable("id")Long id) {
		Campaign campaign = campaignService.getById(id);
		model.addAttribute("campaign",campaign);
	    return "timespent";
    }
	
}
