package com.levi9.prodavnica.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.service.BookService;

@RestController
@RequestMapping("api/books")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Value("${topSellingBooks.limit:5}")
	public int topSellingBooksLimit;

	@GetMapping
	public ResponseEntity<?> getAllBooks() {
		return ResponseEntity.ok(bookService.findAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOneBook(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.findBook(id));
	}


	@PreAuthorize(value = "hasAuthority('ADMIN') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody @Validated AddUpdateBookDTO addUpdateBookDTO) {
		return ResponseEntity.ok(bookService.addBook(addUpdateBookDTO));
	}
	@PreAuthorize(value = "hasAuthority('ADMIN') or hasRole('ADMIN')")
	@PutMapping("/{idBook}")
	public ResponseEntity<?> updateBook(@RequestBody @Validated AddUpdateBookDTO bookRequest, @PathVariable long idBook) {
		return ResponseEntity.ok(bookService.updateBook(bookRequest, idBook));
	}

	@GetMapping("/topSellingBooksLimit")
	public ResponseEntity<?> getTopSellingBooks() {
		return ResponseEntity.ok(bookService.getTopSellingBooks(topSellingBooksLimit));
	}

	@RequestMapping(value = "/getBooksFilter", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBooksFromCategories(@RequestParam(name = "id",required = false) Set<Long> id, @RequestParam("search") String search) {
		return ResponseEntity.ok(bookService.getBooksFilter(id,search));
	}

}
