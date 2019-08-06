package com.levi9.prodavnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.UpdateBookDTO;
import com.levi9.prodavnica.service.BookService;

@RestController
@RequestMapping("api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	public ResponseEntity<?> getAllBooks() {

		return null;
	}

	@PutMapping("update/{idBook}")
	public ResponseEntity<?> updateBook(@RequestBody UpdateBookDTO bookRequest, @PathVariable long idBook) {
		return ResponseEntity.ok(bookService.updateBook(bookRequest, idBook));
	}

}
