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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.doctorbari.dto.AccountReportDto;
import com.doctorbari.dto.AmbBookingDto;
import com.doctorbari.dto.BloodBookingDto;
import com.doctorbari.dto.DoctorAppoinmentDtoList;
import com.doctorbari.dto.DoctorDropDownDto;
import com.doctorbari.dto.DoctorSetupDtoSave;
import com.doctorbari.dto.DoctorSetupDtoUpdate;
import com.doctorbari.dto.DoctorTBListDto;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.dto.PersonalMsgDto;
import com.doctorbari.dto.PostDto;
import com.doctorbari.dto.ProfileDto;
import com.doctorbari.dto.RegistrationDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Ambulance;
import com.doctorbari.model.Blood;
import com.doctorbari.model.Contact;
import com.doctorbari.model.Diagnostic;
import com.doctorbari.model.Disease;
import com.doctorbari.model.Payment;
import com.doctorbari.model.Post;
import com.doctorbari.model.User;
import com.doctorbari.service.AmbBookingService;
import com.doctorbari.service.AmbulanceService;
import com.doctorbari.service.BloodBookingService;
import com.doctorbari.service.BloodService;
import com.doctorbari.service.ContactService;
import com.doctorbari.service.DiagnosticService;
import com.doctorbari.service.DiseaseService;
import com.doctorbari.service.DoctorAppoinmentService;
import com.doctorbari.service.DoctorDetailsService;
import com.doctorbari.service.PaymentService;
import com.doctorbari.service.PersonalMsgService;
import com.doctorbari.service.PostService;
import com.doctorbari.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Controller
@RequestMapping("/admin/ajax")
public class AdminControllerAjax {
	
	@Autowired
    private ApplicationContext applicationContext;

	@Autowired
	private BloodService bloodService;

	@Autowired
	private AmbulanceService ambulanceService;

	@Autowired
	private UserService userService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private PostService postService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private DoctorDetailsService doctorDetailService;
	
	@Autowired
	private AmbBookingService ambBookingService;
	
	@Autowired
	private BloodBookingService bloodBookingService;
	
	@Autowired
	private PersonalMsgService pMsgService;
	
	@Autowired
	private DoctorAppoinmentService doctorAppoinmentService;
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private PaymentService paymentService;
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	

	@PostMapping("/saveBlood")
	@ResponseBody
	public CommonMsg saveBlood(@RequestBody InsertDto insertDto) {
		return bloodService.saveBlood(insertDto);
	}

	@GetMapping(value = "/deleteBlood")
	@ResponseBody
	public CommonMsg deleteBlood(@RequestParam("id") Long id) {
		return bloodService.deleteBlood(id);
	}

	@GetMapping(value = "/get-blood-list")
	@ResponseBody
	public List<Blood> listOfBlood() {
		return bloodService.listOfBlood();
	}

	//// Blood

	@PostMapping("/saveAmbulance")
	@ResponseBody
	public CommonMsg saveAmbulance(@RequestBody InsertDto insertDto) {
		return ambulanceService.saveAmbulance(insertDto);
	}

	@GetMapping(value = "/deleteAmbulance")
	@ResponseBody
	public CommonMsg deleteAmbulance(@RequestParam("id") Long id) {
		return ambulanceService.deleteAmbulance(id);
	}

	@GetMapping(value = "/get-ambulance-list")
	@ResponseBody
	public List<Ambulance> listOfAmbulance() {
		return ambulanceService.listOfAmbulance();
	}

	// Ambulance

	//// Disease

	@PostMapping("/saveDisease")
	@ResponseBody
	public CommonMsg saveDisease(@RequestBody InsertDto insertDto) {
		return diseaseService.saveDisease(insertDto);
	}

	@GetMapping(value = "/deleteDisease")
	@ResponseBody
	public CommonMsg deleteDisease(@RequestParam("id") Long id) {
		return diseaseService.deleteDisease(id);
	}

	@GetMapping(value = "/get-disease-list")
	@ResponseBody
	public List<Disease> listOfDisease() {
		return diseaseService.listOfDisease();
	}

	// profile

	@PostMapping("/updateProfile")
	@ResponseBody
	public CommonMsg updateProfile(@RequestBody ProfileDto profileDtom) {
		return userService.updateProfileInfo(profileDtom);
	}

	// post

	@PostMapping("/savePost")
	@ResponseBody
	public CommonMsg savePost(@RequestBody PostDto postDto) {
		return postService.saveUPost(postDto);
	}

	@GetMapping(value = "/get-post-list")
	@ResponseBody
	public List<Post> listOfPost() {
		return postService.listOfPost();
	}

	@GetMapping(value = "/deletePost")
	@ResponseBody
	public CommonMsg deletePost(@RequestParam("postId") Long postId) {
		return postService.deletePost(postId);
	}

	// contact

	@GetMapping("/updateContact")
	@ResponseBody
	public CommonMsg upateContact(@RequestParam("contactId") Long contactId) {
		return contactService.upateContact(contactId);
	}

	@GetMapping(value = "/get-contact-list")
	@ResponseBody
	public List<Contact> listOfContact() {
		return contactService.contactList();
	}

	@GetMapping(value = "/deleteContact")
	@ResponseBody
	public CommonMsg deleteContact(@RequestParam("contactId") Long contactId) {
		return contactService.deleteContact(contactId);
	}

	// doctor

	@PostMapping("/saveDoctor")
	@ResponseBody
	public CommonMsg saveDoctor(@RequestBody RegistrationDto regDto) {
		return userService.doctorSetup(regDto);
	}

	@GetMapping(value = "/get-doctor-list")
	@ResponseBody
	public List<User> listOfDoctor() {
		return userService.doctorList();
	}

	@GetMapping(value = "/deleteDoctor")
	@ResponseBody
	public CommonMsg deleteDoctor(@RequestParam("id") Long id) {
		return userService.deleteDoctor(id);
	}

	@GetMapping(value = "/get-doctor-dropDown")
	@ResponseBody
	public List<DoctorDropDownDto> getDoctorList() {
		return userService.getDoctorList();
	}

	// doctor details
	
	@PostMapping("/saveDoctorDetails")
	@ResponseBody
	public CommonMsg saveDoctorDetails(@RequestParam(value = "doctorData", required = true) String setupDto,
			@RequestParam(value = "doctorDataUpdate", required = true) String doctorDataUpdate,
			@RequestParam(value = "file", required = true) MultipartFile pImg,
			@RequestParam(value = "cfile", required = true) MultipartFile cFile)
			throws Exception{
		
		DoctorSetupDtoSave doctorSetupDto = objectMapper.readValue(setupDto, DoctorSetupDtoSave.class);
	    DoctorSetupDtoUpdate doctorSetupDtoUpdate = objectMapper.readValue(doctorDataUpdate, DoctorSetupDtoUpdate.class);
		return doctorDetailService.saveDoctorDetails(doctorSetupDto,doctorSetupDtoUpdate, pImg, cFile);
	}
	
	@GetMapping(value = "/get-doctor-tbl")
	@ResponseBody
	public List<DoctorTBListDto> doctorDetailsList() {
		return doctorDetailService.doctorTBList();
	}
	
	// ambulance booking
	
	@GetMapping(value = "/view-amb-booking")
	@ResponseBody
	public List<AmbBookingDto> viewAmbBooking() {
		return ambBookingService.listOfAmbBooking();
	}
	
	@GetMapping(value = "/approve-amb-booking")
	@ResponseBody
	public CommonMsg confirmAmbBooking(@RequestParam("id") Long id) {
		return ambBookingService.confirmAmbBooking(id);
	}
	
	@GetMapping(value = "/delete-amb-booking")
	@ResponseBody
	public CommonMsg deleteAmbBooking(@RequestParam("id") Long id) {
		return ambBookingService.deleteAmbBooking(id);
	}
	
	// blood booking

	@GetMapping(value = "/view-blood-booking")
	@ResponseBody
	public List<BloodBookingDto> viewBloodBooking() {
		return bloodBookingService.listOfBloodBooking();
	}
	
	@GetMapping(value = "/approve-blood-booking")
	@ResponseBody
	public CommonMsg confirmBloodBooking(@RequestParam("id") Long id) {
		return bloodBookingService.confirmBloodBooking(id);
	}
	
	@GetMapping(value = "/delete-blood-booking")
	@ResponseBody
	public CommonMsg deleteBloodBooking(@RequestParam("id") Long id) {
		return bloodBookingService.deleteBloodBooking(id);
	}
	
	//personal Message
	
	@GetMapping(value = "/view-per-msg")
	@ResponseBody
	public List<PersonalMsgDto> viewPersonalMsg() {
		return pMsgService.personalMsgList();
	}
	
	@GetMapping(value = "/approve-per-msg")
	@ResponseBody
	public CommonMsg confirmPerMsgBooking(@RequestParam("id") Long id,@RequestParam("rMsg") String rMsg) {
		return pMsgService.upatePersonalMsg(id,rMsg);
	}
	
	@GetMapping(value = "/delete-per-msg")
	@ResponseBody
	public CommonMsg deletePerMsgBooking(@RequestParam("id") Long id) {
		return pMsgService.deletePersonalMsg(id);
	}

	//user setup
	
	@GetMapping(value = "/get-user-list")
	@ResponseBody
	public List<User> viewUserList() {
		return userService.UserList();
	}
	
	@GetMapping(value = "/admin-doc-app-list")
	@ResponseBody
	public List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyDoctor() {
        return doctorAppoinmentService.listOfDoctorAppoinmentbyAdmin();
	}
	
	@GetMapping(value = "/delete-doctor-appoinment")
	@ResponseBody
	public CommonMsg deleteDoctorAppoinment(@RequestParam("id") Long id) {
		return doctorAppoinmentService.deleteDoctorAppoinment(id);
	}
	
	//diagonistices center
	
	@PostMapping("/saveDiagnostic")
	@ResponseBody
	public CommonMsg saveDiagnostic(@RequestBody InsertDto insertDto) {
		return diagnosticService.saveDiagnostic(insertDto);
	}
	
	@GetMapping(value = "/view-diagonistic-center")
	@ResponseBody
	public List<Diagnostic> viewDiagonistic() {
		return diagnosticService.listOfDiagnostic();
	}
	
	@GetMapping(value = "/delete-diagonistic")
	@ResponseBody
	public CommonMsg deleteDiagonistic(@RequestParam("id") Long id) {
		return diagnosticService.deleteDiagnostic(id);
	}
	
	
	@GetMapping(path = "accountReport")
    @ResponseBody
    public void accountEmpReport(HttpServletResponse response,@RequestParam("id") Long userId) throws Exception {
           
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("logo", "static/assets/img/banner.PNG");
        
        List<AccountReportDto> getReportData = paymentService.accountReport(userId);
        Resource resource = applicationContext.getResource("classpath:templates/reports/acc-report.jrxml");
        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        JRDataSource dataSource = new JRBeanCollectionDataSource(getReportData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}
	
	@GetMapping(value = "/view-account-details")
	@ResponseBody
	public List<AccountReportDto> accountDetailsList() {
		return paymentService.accountReportList();
	}
	
	@GetMapping(value = "/delete-payment-details")
	@ResponseBody
	public CommonMsg deleteAccountDetails(@RequestParam("id") Long id) {
		return paymentService.deletePayment(id);
	}
	
	@GetMapping(value = "/delete-doctor-details")
	@ResponseBody
	public CommonMsg deleteDoctorDetails(@RequestParam("id") Long id) {
		return doctorDetailService.deleteDoctorDetails(id);
	}
	
}
