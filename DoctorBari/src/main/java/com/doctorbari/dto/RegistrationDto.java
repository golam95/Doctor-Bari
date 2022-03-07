package com.doctorbari.dto;

import lombok.Data;

@Data 
public class RegistrationDto {
	
	private String updateId;
	
	private String userName;

	private String firstname;

	private String email;

	private String phone;

	private String password;
	
	private String newPassword;

}
