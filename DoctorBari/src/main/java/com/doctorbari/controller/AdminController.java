package com.doctorbari.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.doctorbari.model.User;
import com.doctorbari.service.DiseaseService;
import com.doctorbari.service.PersonalMsgService;
import com.doctorbari.service.PostService;
import com.doctorbari.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PersonalMsgService personalMsgService;

	@GetMapping("/home")
	public String homeFrom(Model model) {
		model.addAttribute("totalUniversity", userService.countByrolename("ROLE_USER"));
		model.addAttribute("totalContact", userService.countByrolename("ROLE_DOCTOR"));
		model.addAttribute("totalPost",postService.totalPost());
		model.addAttribute("totalUrl", personalMsgService.totalMsg());
		return "home";
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
		return "profile-setup";
	}
	
	@GetMapping("/amb-setup")
	public String ambulanceSetupFrm() {
		return "ambulance-setup";
	}
	
	@GetMapping("/disease-setup")
	public String diseaseSetupFrm() {
		return "disease-setup";
	}
	
	@GetMapping("/blood-setup")
	public String bloodSetupFrm() {
		return "blood-setup";
	}
	
	@GetMapping("/blog-post")
	public String blogPostFrom() {
		return "blog-post-setup";
	}
	
	@GetMapping("/contact")
	public String contactFrom(Model model) {
		return "user-contact";
	}
	
	@GetMapping("/doctor")
	public String addDoctor(Model model) {
		return "doctor-setup";
	}
	
	@GetMapping("/doctor-details")
	public String doctorDetails(Model model) {
		model.addAttribute("listDiseaseMap", diseaseService.listOfDisease());
		return "doctor-detail-setup";
	}
	
	@GetMapping("/viewAmbBooking")
	public String viewAmbBooking(Model model) {
		return "view-amb-booking";
	}
	
	@GetMapping("/viewBloodBooking")
	public String viewBloodBooking(Model model) {
		return "view-blood-booking";
	}
	
	@GetMapping("/pMsg")
	public String viewPersonalMsg(Model model) {
		return "view-personal-msg";
	}
	
	@GetMapping("/viewUser")
	public String viewUser(Model model) {
		return "view-user";
	}
	
	@GetMapping("/viewApp")
	public String viewAppoinmentList(Model model) {
		return "view-doctor-appList";
	}
	
	@GetMapping("/empReport")
	public String empReport(Model model) {
		return "view-emp-report";
	}
	
	@GetMapping("/diagnostic-setup")
	public String diagnosticSetupFrm(Model model) {
		return "diagnostic-setup";
	}
	
	@GetMapping("/accountReport")
	public String accountReport(Model model) {
		model.addAttribute("listOfDoctor", userService.doctorList());
		return "account-report";
	}
	
	@GetMapping("/accDetails")
	public String accDetails(Model model) {
		return "account-details";
	}
	
	

}
