package com.dmcliver.springvalidation.dataaccess;

import com.dmcliver.springvalidation.domain.Customer;

public interface CustomerDao {
	
	boolean customerExists(Customer c);
	Customer findByUsername(String userName);
	void save(Customer customer);
}