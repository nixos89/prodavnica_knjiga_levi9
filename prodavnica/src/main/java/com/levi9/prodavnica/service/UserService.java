package com.levi9.prodavnica.service;

import com.levi9.prodavnica.dto.JwtAuthenticationResponse;
import com.levi9.prodavnica.dto.LoginDTO;

public interface UserService {

	public JwtAuthenticationResponse login(LoginDTO userRequest);

}
