package com.doctorbari.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctorbari.model.User;
import com.doctorbari.service.UserService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	public String homeFrom(Model model) {
		return "doctor/dashboard";
	}
	
	@GetMapping("/profile")
	public String adminProfile(Model model, Principal principal) {
		if (principal != null) {
			User getUser = userService.findByUserName(principal.getName());
			model.addAttribute("userProfile", getUser);
		} else {
			model.addAttribute("userProfile", new User());
			return "login";
		}
		return "doctor/d-profile-setup";
	}
	
	

}
