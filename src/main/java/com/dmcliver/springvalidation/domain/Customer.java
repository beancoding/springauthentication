package com.dmcliver.springvalidation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name="Customer")
public class Customer {

	@Id
	@Column(name="UserName")
	@NotEmpty @NotNull
	private String userName;
	
	@Column(name="Password",nullable=false)
	@NotEmpty @NotNull
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).*$",message="Password must contain at least 1 capital & 1 number")
	@Length(min=8,message="Password must be at least 8 chars")
	private String password;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
