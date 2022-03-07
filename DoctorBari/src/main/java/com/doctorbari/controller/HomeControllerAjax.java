package com.doctorbari.controller;

import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.doctorbari.dto.AmbBookingDto;
import com.doctorbari.dto.BloodBookingDto;
import com.doctorbari.dto.ContactDto;
import com.doctorbari.dto.DiseaseSearchDtoList;
import com.doctorbari.dto.DoctorAppoinmentDtoList;
import com.doctorbari.dto.InsertBookingDto;
import com.doctorbari.dto.PersonalMsgDto;
import com.doctorbari.dto.ProfileDto;
import com.doctorbari.dto.RegistrationDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Ambulance;
import com.doctorbari.model.User;
import com.doctorbari.service.AmbBookingService;
import com.doctorbari.service.AmbulanceService;
import com.doctorbari.service.BloodBookingService;
import com.doctorbari.service.ContactService;
import com.doctorbari.service.DoctorAppoinmentService;
import com.doctorbari.service.DoctorDetailsService;
import com.doctorbari.service.PersonalMsgService;
import com.doctorbari.service.UserService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/user")
public class HomeControllerAjax {
	
	@Autowired
    private ApplicationContext applicationContext;

	@Autowired
	private UserService userService;

	@Autowired
	private AmbBookingService ambBookingService;

	@Autowired
	private AmbulanceService ambulanceService;

	@Autowired
	private BloodBookingService bloodBookingService;

	@Autowired
	private DoctorDetailsService doctorDetailsService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private DoctorAppoinmentService doctorAppoinmentService;

	@Autowired
	private PersonalMsgService personalMsgService;

	@PostMapping("/saveReg")
	@ResponseBody
	public CommonMsg saveRegistration(@RequestBody RegistrationDto reg) {
		return userService.userReg(reg);
	}

	@GetMapping("/profile")
	public String userProfile(Model model, Principal principal) {
		if (principal != null) {
			User getUser = userService.findByUserName(principal.getName());
			model.addAttribute("usrProfile", getUser);
		} else
			model.addAttribute("usrProfile", new User());
		return "userProfile";
	}

	@PostMapping("/saveAmbBook")
	@ResponseBody
	public CommonMsg saveAmbBook(@RequestBody InsertBookingDto insertDto, Principal principal) {
		return ambBookingService.saveAmbBooking(insertDto, principal.getName());
	}

	@GetMapping(value = "/getPrice")
	@ResponseBody
	public Ambulance findByAmbulanceId(@RequestParam("id") String id) {
		return ambulanceService.findByAmbulanceId(Long.parseLong(id));
	}

	@PostMapping("/saveBloodBook")
	@ResponseBody
	public CommonMsg saveBloodBook(@RequestBody InsertBookingDto insertDto, Principal principal) {
		return bloodBookingService.saveBloodBooking(insertDto, principal.getName());
	}

	@GetMapping(value = "/searchDoctor")
	@ResponseBody
	public List<DiseaseSearchDtoList> searchDoctor(@RequestParam("id") String id) {
		return doctorDetailsService.searchDisease(id);
	}

	@PostMapping("/saveContact")
	@ResponseBody
	public CommonMsg saveContact(@RequestBody ContactDto contactDto) {
		return contactService.saveContact(contactDto);
	}

	@PostMapping("/saveDoctorAppoinment")
	@ResponseBody
	public CommonMsg saveDoctorAppoinment(@RequestBody InsertBookingDto insertBookingDto, Principal principal) {
		return doctorAppoinmentService.saveDoctorAppoinment(insertBookingDto, principal.getName());
	}

	@GetMapping(value = "/user-amb-book-list")
	@ResponseBody
	public List<AmbBookingDto> listOfAmbBooking(Principal principal) {
		return ambBookingService.listOfAmbBookingByUserId(principal.getName());
	}

	@GetMapping(value = "/user-blood-book-list")
	@ResponseBody
	public List<BloodBookingDto> listOfBloodBookingByUserId(Principal principal) {
		return bloodBookingService.listOfBloodBookingByUserId(principal.getName());
	}

	@GetMapping(value = "/user-doc-appo-list")
	@ResponseBody
	public List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyUser(Principal principal) {
		return doctorAppoinmentService.listOfDoctorAppoinmentbyUser(principal.getName());
	}

	@PostMapping("/savePersonalMsg")
	@ResponseBody
	public CommonMsg savePersonalMsg(@RequestBody ContactDto contact, Principal principal) {
		return personalMsgService.savePersonalMsg(contact, principal.getName());
	}

	@GetMapping(value = "/user-per-app-list")
	@ResponseBody
	public List<PersonalMsgDto> listOfpersonalMsgList(Principal principal) {
		return personalMsgService.personalMsgListById(principal.getName());
	}

	@PostMapping("/updateProfile")
	@ResponseBody
	public CommonMsg updateProfile(@RequestBody ProfileDto profileDtom) {
		return userService.updateProfileInfo(profileDtom);
	}

	@PostMapping("/resetPassword")
	@ResponseBody
	public CommonMsg resetPassword(@RequestBody User user) {
		return userService.resetPassword(user);
	}
	
	
	@GetMapping(path = "empReport")
    @ResponseBody
    public void getDepEmpReport(HttpServletResponse response) throws Exception {
                                 
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo", "static/assets/img/banner.PNG");
        
        List<User> getReportData = userService.UserList();
        Resource resource = applicationContext.getResource("classpath:templates/reports/emp-report.jrxml");
        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        JRDataSource dataSource = new JRBeanCollectionDataSource(getReportData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
	
	@GetMapping(path = "docReport")
    @ResponseBody
    public void getDocReport(HttpServletResponse response) throws Exception {
                                 
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo", "static/assets/img/banner.PNG");
        
        List<User> getReportData = userService.doctorList();
        Resource resource = applicationContext.getResource("classpath:templates/reports/doc-report.jrxml");
        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        JRDataSource dataSource = new JRBeanCollectionDataSource(getReportData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

	

}
