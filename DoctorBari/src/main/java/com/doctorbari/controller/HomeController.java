package com.doctorbari.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.doctorbari.dto.DiseaseSearchDtoList;
import com.doctorbari.helper.Helper;
import com.doctorbari.model.Post;
import com.doctorbari.model.User;
import com.doctorbari.service.AmbulanceService;
import com.doctorbari.service.BloodService;
import com.doctorbari.service.DiseaseService;
import com.doctorbari.service.DoctorDetailsService;
import com.doctorbari.service.PostService;
import com.doctorbari.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;

	@Autowired
	private AmbulanceService ambulanceService;

	@Autowired
	private BloodService bloodService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private DoctorDetailsService doctorDetailService;

	@Autowired
	private Helper helper;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String indexFrom(Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				return "index";
			}
		} catch (Exception ex) {
			return "index";
		}
	}

	@GetMapping("/reg")
	public String regFrom(Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				return "registration";
			}
		} catch (Exception ex) {
			return "registration";
		}
	}

	@GetMapping("/login")
	public String loginFrom(Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				return "login";
			}
		} catch (Exception ex) {
			return "login";
		}
	}

	@GetMapping("/dashboard")
	public String dashboardFrom(Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				return "userDashboard";
			}
		} catch (Exception ex) {
			return "index";
		}
	}

	@GetMapping("/amb")
	public String ambulanceBookingFrom(Model model, Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				model.addAttribute("listAmbulanceMap", ambulanceService.listOfAmbulance());
				return "ambBooking";
			}
		} catch (Exception ex) {
			return "index";
		}
	}

	@GetMapping("/blood")
	public String bloodBookingFrom(Model model, Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				model.addAttribute("listBloodMap", bloodService.listOfBlood());
				return "bloodBooking";
			}
		} catch (Exception ex) {
			return "index";
		}
	}

	@GetMapping("/findDoctor")
	public String findDoctorFrom(Model model, Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				model.addAttribute("listDiseaseMap", diseaseService.listOfDisease());
				return "findDoctor";
			}
		} catch (Exception ex) {
			return "index";
		}
	}

	@GetMapping("/appoinment")
	public String doctorAppoinmentFrom(@RequestParam("sp") Long userId, Model model, Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				DiseaseSearchDtoList doctorDetailsList = doctorDetailService.doctorDetailsList(userId);
				model.addAttribute("doctorDetailsList", doctorDetailsList);
				model.addAttribute("empPhotoImg", helper.getImgByBLob(doctorDetailsList.getImage()));
				model.addAttribute("empCertificateImg", helper.getImgByBLob(doctorDetailsList.getCertificate()));
				return "doctorAppoinment";
			}
		} catch (Exception ex) {
			return "index";
		}
	}

	@GetMapping("/profile")
	public String userProfileFrom(Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				return "userProfile";
			}
		} catch (Exception ex) {
			return "index";
		}
	}

	@GetMapping("/access-denied")
	public String accessdeniedFrom() {
		return "access-denied";
	}

	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String listBooks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, Principal principal) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);
		Page<Post> bookPage = postService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("bookPage", bookPage);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "user-post";
	}
	
	@GetMapping("/resetpass")
	public String userPasswordFrom(Principal principal) {
		try {
			User user = userService.findByUserName(principal.getName());
			if (user.getRolename().equals("ROLE_DOCTOR")) {
				return "redirect:./doctor/dashboard";
			} else if (user.getRolename().equals("ROLE_ADMIN")) {
				return "redirect:./admin/home";
			} else {
				return "user-reset";
			}
		} catch (Exception ex) {
			return "user-reset";
		}
	}
}
