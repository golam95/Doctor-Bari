package com.doctorbari.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.doctorbari.dto.ConfirmAppoinmentDto;
import com.doctorbari.dto.DoctorAppoinmentDtoList;
import com.doctorbari.dto.InsertBookingDto;
import com.doctorbari.helper.CommonMsg;

public interface DoctorAppoinmentService {
	
	CommonMsg saveDoctorAppoinment(InsertBookingDto saveDto,String username);
	
	List<DoctorAppoinmentDtoList> listOfDoctorAppoinment();
	
	List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyUser(String id);
	
	CommonMsg deleteDoctorAppoinment(Long id);
	
	List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyDoctor(Long id);
	
    CommonMsg confirmAppoinmentReject(ConfirmAppoinmentDto confirm);
	
	CommonMsg confirmAppoinmentMeeting(ConfirmAppoinmentDto confirm);
	
	CommonMsg confirmAppoinmentFile(ConfirmAppoinmentDto confirm, MultipartFile file) throws Exception;

	
	List<DoctorAppoinmentDtoList> listOfDoctorAppoinmentbyAdmin();

}
