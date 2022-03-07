package com.doctorbari.service;

import java.io.IOException;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import com.doctorbari.dto.ContactDto;
import com.doctorbari.dto.DiseaseSearchDtoList;
import com.doctorbari.dto.DoctorSetupDto;
import com.doctorbari.dto.DoctorSetupDtoSave;
import com.doctorbari.dto.DoctorSetupDtoUpdate;
import com.doctorbari.dto.DoctorTBListDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.DoctorDetails;

public interface DoctorDetailsService {
	
	CommonMsg saveDoctorDetails(DoctorSetupDtoSave doctorSetupDtoSave,DoctorSetupDtoUpdate doctorSetupDtoUpdate,MultipartFile prImg,MultipartFile certiImg)throws IOException;
	
	DiseaseSearchDtoList doctorDetailsList(Long id);
	
	List<DiseaseSearchDtoList> searchDisease(String id);
	
	List<DoctorTBListDto> doctorTBList();
	
	CommonMsg deleteDoctorDetails(Long id);
	
}
