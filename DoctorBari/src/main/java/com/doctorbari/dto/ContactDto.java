package com.doctorbari.dto;

import lombok.Data;

@Data
public class ContactDto {
	private String userName;
	private String userEmail;
	private String userSubject;
	private String userMessage;
	private String updateId;
	private String replyMsg;
}
