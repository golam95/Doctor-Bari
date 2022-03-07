package com.doctorbari.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.doctorbari.dto.DoctorDropDownDto;
import com.doctorbari.dto.DoctorSetupDto;
import com.doctorbari.dto.ProfileDto;
import com.doctorbari.dto.RegistrationDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.User;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);
	
	CommonMsg updateProfileInfo(ProfileDto profileDtom);
	
	CommonMsg resetPassword(User user);
	
	CommonMsg doctorSetup(RegistrationDto regDto);
	
	CommonMsg userReg(RegistrationDto regDto);
	
	List<User> doctorList();
	
	List<User> UserList();
	
	CommonMsg deleteUser(Long id);
	
	CommonMsg deleteDoctor(Long userId);
	
	List<User> dropDownDoctorList();
	
	List<DoctorDropDownDto> getDoctorList();
	
	long countByrolename(String roleName);
	
}
