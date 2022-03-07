package com.doctorbari.dto;

import lombok.Data;

@Data 
public class DoctorSetupDto {
	
	private String updateId;
	
	private String isVerified;
	
	private String userName;
	
	//
	
	private Long userId;
	
	private String expert;
	
	private double price;
	
	private String universityName;
	
	private String degreeName;
	
	private String about;

    private byte[] profileImg;
	
    private byte[] certificateBlob;

}
