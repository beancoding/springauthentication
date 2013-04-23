package com.dmcliver.springvalidation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Customer")
public class Customer {

	@Id
	@Column(name="UserName")
	@NotEmpty @NotNull
	private String userName;
	
	@Column(name="Password",nullable=false)
	@NotEmpty @NotNull
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
