package com.dmcliver.springvalidation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dmcliver.springvalidation.dataaccess.CustomerDao;
import com.dmcliver.springvalidation.domain.Customer;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao dao){
		this.customerDao =dao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Customer user = customerDao.findByUsername(userName);
		if(user == null)
			throw new UsernameNotFoundException(String.format("User name %s was not found", userName));
		return new UserDetailsImpl(user);
	}
}
