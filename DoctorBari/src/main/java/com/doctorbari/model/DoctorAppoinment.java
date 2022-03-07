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
@Table(name = "doctorAppoinment")
public class DoctorAppoinment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long apId;
    
    @Column(name = "uId")
	private Long userId;
    
    @Column(name = "username")
  	private String userName;
    
    @Column(name = "bookingtime")
   	private String bookTime;
    
    @Column(name = "bookingdate")
	private Date bookDate;
    
    @Column(name = "price")
	private double price;
    
    @Column(name = "tranId")
   	private String transactionId;
    
    @Column(name = "status")
   	private String status;
    
    @Column(name = "meeting")
   	private String meetingLink;
    
    @Column(name = "filePath")
   	private String filePath;
    
    @Column(name = "remark")
   	private String remark;
    
    @Column(name = "diaCenter")
    private String diaCenter;
    
    @Column(name = "date")
	private Date date;
}
