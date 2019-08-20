package com.levi9.prodavnica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.prodavnica.dto.LoginDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.repository.UserRepository;
import com.levi9.prodavnica.security.AuthenticatedUser;
import com.levi9.prodavnica.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	AuthenticatedUser authenticationUser;

	@Override
	public boolean login(LoginDTO loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return true;
		} catch (BadCredentialsException e) {
			throw new StoreException(HttpStatus.BAD_REQUEST, "Incorrect password!");
		}

	}

}
