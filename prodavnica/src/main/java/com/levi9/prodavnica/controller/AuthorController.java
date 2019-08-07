package com.levi9.prodavnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.service.AuthorService;

@RestController
@RequestMapping("api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@GetMapping

	public ResponseEntity<?> getAllAuthors() {
		return ResponseEntity.ok(authorService.findAllAuthors());
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getAuthor(@PathVariable Long id) {
		return ResponseEntity.ok(authorService.getOne(id));
	}

}
