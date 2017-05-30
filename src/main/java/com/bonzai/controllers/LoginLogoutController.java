package com.bonzai.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonzai.model.User;
import com.bonzai.repository.UserRepository;

@Controller
@RequestMapping("/console")
public class LoginLogoutController {

	final static Logger log = Logger.getLogger(LoginLogoutController.class);
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/login")
	public String login(HttpSession session){
		//session.setAttribute("email", email);
		//session.setAttribute("role", role);
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		return "redirect:/console/login";
	}
	
	@RequestMapping("/signup")
	public String signup(){
	    return "signup";
	}

	@RequestMapping("/profile")
	public String profile(Model model,HttpSession session){
		User user = userRepository.findUserByName((String)session.getAttribute("userEmailId"));
		log.debug("user=="+user);
		model.addAttribute("user", user);
		return "profile";
	}
}
