package com.bonzai.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bonzai.model.Campaign;
import com.bonzai.model.User;
import com.bonzai.repository.CampaignRepository;
import com.bonzai.services.CampaignService;
import com.bonzai.services.UserService;

@Configuration
@ComponentScan(basePackages={"com.*"})
@PropertySource("classpath:/user.properties")
@Controller
@RequestMapping("/user")
public class UserController {

	final static Logger log = Logger.getLogger(UserController.class);
	
	@Value("${user.statusnotlike}")
	private String status_not_like;
	
	@Autowired
	CampaignService campaignService;
	
	@Autowired
	CampaignRepository campaignRepository;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/dashboard")
	public String userDashboard(Model model){
		List<Campaign> campaigns = campaignRepository.findByStatusNotLike(status_not_like);
		log.debug("list::"+campaigns);
		model.addAttribute("campaigns", campaigns);
		return "userdashboard";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user){
		userService.save(user);
		log.debug("users : "+user);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
}
