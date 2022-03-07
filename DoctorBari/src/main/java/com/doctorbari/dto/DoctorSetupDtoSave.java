package com.doctorbari.dto;

import lombok.Data;

@Data 
public class DoctorSetupDtoSave {
	
	private Long userId;
	
	private String expert;
	
	private double price;
	
	private String universityName;
	
	private String degreeName;
	
	private String about;

}
