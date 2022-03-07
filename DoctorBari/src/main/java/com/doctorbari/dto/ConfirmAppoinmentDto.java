package com.doctorbari.dto;

import lombok.Data;

@Data 
public class ConfirmAppoinmentDto {
	private String remark;
	private String meetingLink;
	private Long appointId;
	private String filePath;
	private String diaCenter;
	
}
