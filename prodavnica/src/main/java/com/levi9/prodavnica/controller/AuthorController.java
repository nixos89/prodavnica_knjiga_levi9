package com.levi9.prodavnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.service.AuthorService;

@RestController
@RequestMapping("api/author")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
}
