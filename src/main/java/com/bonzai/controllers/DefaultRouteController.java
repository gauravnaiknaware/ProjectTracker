package com.bonzai.controllers;

import com.bonzai.model.User;
import com.bonzai.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Controller
public class DefaultRouteController {

	final static Logger log = Logger.getLogger(DefaultRouteController.class);
	
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping("/")
	public String defaultRoute() {
		return "redirect:/console/login";
	}
	
	@RequestMapping(value="/validate",method=RequestMethod.GET)
	public String validateUser(@RequestParam("email") String email, @RequestParam("password")String password,HttpSession session) throws UnsupportedEncodingException{
		User user = userRepository.findUserByName(email);
		if(user == null){
			return "redirect:/console/login";
		}
		
		String pass = new String( Base64.getDecoder().decode(user.getPassword()), "utf-8" );
		if( email.trim().equals(user.getName().trim()) && password.trim().equals(pass.trim()) ) {

			session.setAttribute("userId",user.getId());
			session.setAttribute("userEmailId", email);
			session.setAttribute("role", user.getRole());

			return "redirect:/console/dashboard";
		}
		return "redirect:/console/login";
	
	}
	
	 public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	 }
}
