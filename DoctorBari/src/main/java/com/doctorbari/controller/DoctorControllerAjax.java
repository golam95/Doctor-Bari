package com.doctorbari.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.doctorbari.dto.ConfirmAppoinmentDto;
import com.doctorbari.dto.DoctorAppoinmentDtoList;
import com.doctorbari.dto.DoctorSetupDtoSave;
import com.doctorbari.dto.DoctorSetupDtoUpdate;
import com.doctorbari.dto.InsertDto;
import com.doctorbari.dto.ProfileDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Blood;
import com.doctorbari.model.Diagnostic;
import com.doctorbari.model.User;
import com.doctorbari.service.DiagnosticService;
import com.doctorbari.service.DoctorAppoinmentService;
import com.doctorbari.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

@Controller
@RequestMapping("/doctor/ajax")
public class DoctorControllerAjax {

	@Autowired
	private DoctorAppoinmentService doctorAppoinmentService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private DiagnosticService diagnosticService;
	

	ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping(value = "/doctor-dashboard-list")
	@ResponseBody
	public List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyDoctor(Principal pricipal) {
		User user = userService.findByUserName(pricipal.getName());
		return doctorAppoinmentService.listOfDoctorAppoinmentbyDoctor(user.getUserid());
	}

	@PostMapping("/saveMeetingLink")
	@ResponseBody
	public CommonMsg saveMeetingLink(@RequestBody ConfirmAppoinmentDto confirm) {
		return doctorAppoinmentService.confirmAppoinmentMeeting(confirm);
	}

	@PostMapping("/saveMeetingFile")
	@ResponseBody
	public CommonMsg saveMeetingFile(@RequestParam(value = "uniList", required = true) String setupDto,
			@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
		ConfirmAppoinmentDto confirmDto = objectMapper.readValue(setupDto, ConfirmAppoinmentDto.class);
		return doctorAppoinmentService.confirmAppoinmentFile(confirmDto, file);
	}

	@PostMapping("/saveMeetingReject")
	@ResponseBody
	public CommonMsg confirmAppoinmentReject(@RequestBody ConfirmAppoinmentDto confirm) {
		return doctorAppoinmentService.confirmAppoinmentReject(confirm);
	}
	
	@PostMapping("/d-updateProfile")
	@ResponseBody
	public CommonMsg updateProfile(@RequestBody ProfileDto profileDtom) {
		return userService.updateProfileInfo(profileDtom);
	}
	
	@GetMapping("/getDiagonstic")
	@ResponseBody
	public List<Diagnostic> getDiagonstic() {
		return diagnosticService.listOfDiagnostic();
	}
}
