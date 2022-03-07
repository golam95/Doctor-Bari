package com.doctorbari.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Lob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doctorbari.dto.DiseaseSearchDtoList;
import com.doctorbari.dto.DoctorSetupDto;
import com.doctorbari.dto.DoctorSetupDtoSave;
import com.doctorbari.dto.DoctorSetupDtoUpdate;
import com.doctorbari.dto.DoctorTBListDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Blood;
import com.doctorbari.model.DoctorDetails;
import com.doctorbari.repository.DoctorDetailsRepository;
import com.doctorbari.service.DoctorDetailsService;

@Service
public class DoctorDetailsServiceImpl implements DoctorDetailsService {
	
	@Autowired
	private DoctorDetailsRepository doctorDetilsReporsitory;

	@Override
	public CommonMsg saveDoctorDetails(DoctorSetupDtoSave doctorSetupDtoSave,DoctorSetupDtoUpdate doctorSetupDtoUpdate,MultipartFile prImg,MultipartFile certiImg)throws IOException {
		CommonMsg commonMsg = new CommonMsg();
		DoctorDetails doctorDetails = new DoctorDetails();
		if (doctorSetupDtoUpdate.getUpdateId().isEmpty()) {
			DoctorDetails checkName = doctorDetilsReporsitory.findByuserId(doctorSetupDtoSave.getUserId());
			if (checkName != null) {
				commonMsg.setMsgCode("201");
			} else {
				doctorDetails.setUserId(doctorSetupDtoSave.getUserId());
				doctorDetails.setExpert(doctorSetupDtoSave.getExpert());
				doctorDetails.setPrice(doctorSetupDtoSave.getPrice());
				doctorDetails.setUniversityName(doctorSetupDtoSave.getUniversityName());
				doctorDetails.setDegreeName(doctorSetupDtoSave.getDegreeName());
				doctorDetails.setAbout(doctorSetupDtoSave.getAbout());
				doctorDetails.setIsVerified("1");
				doctorDetails.setProfileImg(prImg.getBytes());
				doctorDetails.setCertificateBlob(certiImg.getBytes());
				doctorDetails.setDate(new Date());
				doctorDetilsReporsitory.save(doctorDetails);
				commonMsg.setMsgCode("200");
			}
		} else {
			doctorDetails=doctorDetilsReporsitory.findBydoctorId(Long.parseLong(doctorSetupDtoUpdate.getUpdateId()));
			doctorDetails.setUserId(doctorSetupDtoSave.getUserId());
			doctorDetails.setExpert(doctorSetupDtoSave.getExpert());
			doctorDetails.setPrice(doctorSetupDtoSave.getPrice());
			doctorDetails.setAbout(doctorSetupDtoSave.getAbout());
			doctorDetails.setUniversityName(doctorSetupDtoSave.getUniversityName());
			doctorDetails.setDegreeName(doctorSetupDtoSave.getDegreeName());
			doctorDetails.setIsVerified("1");
			doctorDetails.setProfileImg(prImg.getBytes());
			doctorDetails.setCertificateBlob(certiImg.getBytes());
			doctorDetails.setDate(new Date());
			doctorDetilsReporsitory.save(doctorDetails);
			commonMsg.setMsgCode("205");
		}
		return commonMsg;
	}

	@Override
	public DiseaseSearchDtoList doctorDetailsList(Long id) {
		return doctorDetilsReporsitory.doctorDetailsList(id);
	}

	@Override
	public List<DiseaseSearchDtoList> searchDisease(String name) {
		return doctorDetilsReporsitory.searchDisease(name);
	}

	@Override
	public List<DoctorTBListDto> doctorTBList() {
		return doctorDetilsReporsitory.doctorTBList();
	}

	@Override
	public CommonMsg deleteDoctorDetails(Long id) {
		CommonMsg commonMsg=new CommonMsg();
		doctorDetilsReporsitory.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

}
