package com.dmcliver.springvalidation.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dmcliver.springvalidation.domain.Customer;

public class UserDetailsImpl implements UserDetails {
		
	private String password;
	private String userName;
	private Collection<GrantedAuthority> grantedAuthorities;

	public UserDetailsImpl(Customer user) {
		grantedAuthorities = new ArrayList<GrantedAuthority>(5);
		password = user.getPassword();
		userName = user.getUserName();
		grantedAuthorities.add(new SimpleGrantedAuthority("user"));
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
