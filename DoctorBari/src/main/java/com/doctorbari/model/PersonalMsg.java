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
@Table(name = "personalMsg")
public class PersonalMsg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long pId;
	
	@Column(name = "username")
	private String username;

	@Column(name = "message")
	private String message;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "replyMsg")
	private String replyMsg;

	@Column(name = "date")
	private Date date;

}
