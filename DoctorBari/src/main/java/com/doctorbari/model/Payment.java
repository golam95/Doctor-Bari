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
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long paymentId;
	
	@Column(name = "uid")
	private Long userId;

	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date")
	private Date date;

}
