package com.doctorbari.dto;

import java.math.BigInteger;
import java.util.Date;
import lombok.Data;


@Data 
public class DoctorAppoinmentDtoList {
	private BigInteger id;
	private String firstname;
	private String time;
	private Date date;
	private double price;
	private String meeting;
	private String filepath;
	private String status;
	private String bkashid;
	private String diaCenter;
	private String remark;
	private String username;
	
}
