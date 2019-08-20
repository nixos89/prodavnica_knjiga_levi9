package com.levi9.prodavnica.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
