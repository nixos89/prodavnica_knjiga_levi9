package com.levi9.prodavnica.controller;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.LoginDTO;
import com.levi9.prodavnica.service.UserService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	UserService userService;

	@PostMapping("login")
	@PermitAll
	public ResponseEntity<?> login(@RequestBody LoginDTO userRequest) {
		return ResponseEntity.ok(userService.login(userRequest));
	}

}
