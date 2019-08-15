package com.levi9.prodavnica.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.service.BookService;

@RestController
@RequestMapping("api/books")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<?> getAllBooks() {
		return ResponseEntity.ok(bookService.findAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOneBook(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.findBook(id));
	}

	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO) {
		return ResponseEntity.ok(bookService.addBook(addUpdateBookDTO));
	}

	@PutMapping("/{idBook}")
	public ResponseEntity<?> updateBook(@RequestBody AddUpdateBookDTO bookRequest, @PathVariable long idBook) {
		return ResponseEntity.ok(bookService.updateBook(bookRequest, idBook));
	}

	@GetMapping({"/search/{search}", "/search"})
	public ResponseEntity<?> searchBooks(@PathVariable Optional<String> search) {
		return ResponseEntity.ok(bookService.searchForBook(search.isPresent() ? search.get() : ""));
	}

}
