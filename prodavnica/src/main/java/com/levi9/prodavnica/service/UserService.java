package com.levi9.prodavnica.service;

import com.levi9.prodavnica.dto.LoginDTO;

public interface UserService {

	public boolean login(LoginDTO loginRequest);

}
