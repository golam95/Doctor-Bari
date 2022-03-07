package com.doctorbari.dto;

import java.util.Date;

public interface BloodBookingDto {
	Long getId();
	String getName();
	String getBookingtime();
	Date getBookingdate();
	String getstatus();
}
