package com.dmcliver.springvalidation;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class CustomerDaoIntegrationTest {
	
	@Test
	public void encodesPasswordBeforeSaving(){
		
		PasswordEncoder encoder = new ShaPasswordEncoder(256);
		String actualPassword = "Password1";
		String p1 = encoder.encodePassword(actualPassword, "Daniel");
		String p2 = encoder.encodePassword(actualPassword, "Daniels");
		String p3 = encoder.encodePassword(actualPassword, "Daniel");
		
		assertNotSame(p1, actualPassword);
		assertNotSame(p2, actualPassword);
		assertNotSame(p3, actualPassword);
	}
}
