package com.doctorbari.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InsertBookingDto {
	private Long fkId;
	private double price;
	private String bookingTime;
	private String transactionId;
	private Date bookingDate;

}
