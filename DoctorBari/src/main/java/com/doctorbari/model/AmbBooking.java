package com.doctorbari.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ambBook")
public class AmbBooking {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long ambookId;
    
    @Column(name = "ambid")
	private Long ambulanceId;
    
    @Column(name = "username")
  	private String userName;
    
    @Column(name = "bookingtime")
   	private String bookTime;
    
    @Column(name = "status")
   	private String status;
    
    @Column(name = "bookingdate")
	private Date bookDate;
    
    @Column(name = "date")
	private Date date;

}
