package com.levi9.prodavnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.service.BookService;

@RestController
@RequestMapping("api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	public ResponseEntity<?> getAllBooks() {

		return null;
	}

	@PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO){

		return ResponseEntity.ok(bookService.addBook(addUpdateBookDTO));
	}

	@PutMapping("update/{idBook}")
	public ResponseEntity<?> updateBook(@RequestBody AddUpdateBookDTO bookRequest, @PathVariable long idBook) {
		return ResponseEntity.ok(bookService.updateBook(bookRequest, idBook));
	}



}
