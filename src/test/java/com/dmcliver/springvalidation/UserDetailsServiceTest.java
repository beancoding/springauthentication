package com.dmcliver.springvalidation;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.dmcliver.springvalidation.dataaccess.CustomerDao;
import com.dmcliver.springvalidation.domain.Customer;
import com.dmcliver.springvalidation.services.UserDetailsServiceImpl;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {
	
	@InjectMocks
	private UserDetailsService service = new UserDetailsServiceImpl();
	
	@Mock
	private CustomerDao customerStub;
	
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void loadUserByName_WithUserNotFound_ThrowsException(){
		
		String username = "random peep";
		
		Mockito.when(customerStub.findByUsername(anyString())).thenReturn(null);	
		
		expectedEx.expect(UsernameNotFoundException.class);
		expectedEx.expectMessage(String.format("User name %s was not found", username));
		
		service.loadUserByUsername(username);
	}
	
	@Test
	public void loadUserByName_WithUserFound_ReturnsUserDetails(){
		
		Customer c = new Customer();
		
		String expectedName = "Spazz";
		String expectedPassword = "Password1";
		
		c.setUserName(expectedName);
		c.setPassword(expectedPassword);
		
		Mockito.when(customerStub.findByUsername(Mockito.anyString()))
			.thenReturn(c);
				
		UserDetails userDetails = service.loadUserByUsername("random peep");
		
		String password = userDetails.getPassword();
		String username = userDetails.getUsername();
	
		Assert.assertEquals(expectedPassword, password);
		Assert.assertEquals(expectedName, username);
	}
}
