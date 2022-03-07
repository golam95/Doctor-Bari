package com.doctorbari.model;

import javax.persistence.*;
import lombok.Data;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userid;

	@Column(name = "username")
	private String userName;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "password")
	private String password;

	@Column(name = "rolename")
	private String rolename;
	
	@Column(name = "date")
	private Date date;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

}
