package com.dmcliver.springvalidation.dataaccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dmcliver.springvalidation.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public CustomerDaoImpl(){
		
	}
	
	public CustomerDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean customerExists(Customer c){
		
		Session session = sessionFactory.getCurrentSession();
		Customer match = (Customer)session.createCriteria(Customer.class)
				.add(Restrictions.eq("userName", c.getUserName()))
				.add(Restrictions.eq("password", c.getPassword()))
				.uniqueResult();
		return match != null;
	}

	@Override
	@Transactional
	public Customer findByUsername(String userName) {
		
		Session session = sessionFactory.getCurrentSession();
		return (Customer)session.createCriteria(Customer.class)
			.add(Restrictions.eq("userName", userName))
			.setMaxResults(1)
			.uniqueResult();
	}

	@Override
	@Transactional
	public void save(Customer customer) {
		String password = passwordEncoder.encode(customer.getPassword());
		Session session = sessionFactory.getCurrentSession();
		customer.setPassword(password);
		session.save(customer);
	}
}
