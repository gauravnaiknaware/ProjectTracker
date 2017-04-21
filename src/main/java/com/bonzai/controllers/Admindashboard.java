package com.bonzai.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonzai.model.Campaign;
import com.bonzai.repository.CampaignRepository;
import com.bonzai.services.CampaignService;

@Configuration
@ComponentScan(basePackages={"com.*"})
@PropertySource("classpath:/admin.properties")
@Controller
@RequestMapping("/admin")
public class Admindashboard {

	final static Logger log = Logger.getLogger(Admindashboard.class);
	
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
	
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	CampaignRepository campaignRepository;
	
	
	@RequestMapping(value="/dashboard")
	public String adminDashboard(Model model){
		List<Campaign> campaigns = campaignRepository.findByStatusNotLike(status_not_like);
		log.debug("list::"+campaigns);
		model.addAttribute("campaigns", campaigns);
		return "admindashboard";
	}
	
	@RequestMapping(value="/create")
	public String adminCreateTicket(Model model){
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
		return "redirect:/admin/dashboard";
	}
	
	@RequestMapping(value="/view/{id}")
	public String viewTicket(@PathVariable("id") long id,Model model){
		Campaign campaign = campaignService.getById(id);
		model.addAttribute("campaign", campaign);
		
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
		
		return "adminviewticket";
	}
	
}
