package com.piyush004.SportsApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.piyush004.SportsApi.repository.UserRepo;

@Service
public class UsersDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByEmail(username).orElseThrow();
	}
	
}
