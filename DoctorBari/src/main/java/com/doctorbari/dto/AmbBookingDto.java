package com.doctorbari.dto;

import java.util.Date;

public interface AmbBookingDto {
	String getName();
	Long getAmbid();
	Long getid();
	double getPrice();
	String getBookingtime();
	Date getBookingdate();
	String getStatus(); 
}
