package com.doctorbari.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor")
public class DoctorDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long doctorId;
	
	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "experties")
	private String expert;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "uniName")
	private String universityName;
	
	@Column(name = "degName")
	private String degreeName;
	
	@Column(name = "isVerified")
	private String isVerified;
	
	
	@Column(name = "about")
	private String about;
	
	@Lob
    @Column(name = "image")
    private byte[] profileImg;
	
	@Lob
    @Column(name = "certificate")
    private byte[] certificateBlob;
	
	@Column(name = "date")
	private Date date;
	
}
