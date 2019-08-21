package com.levi9.prodavnica.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("api/books")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Value("${topSellingBooks.limit:5}")
	public int topSellingBooksLimit;
	

	@PermitAll
	@GetMapping
	public ResponseEntity<?> getAllBooks() {
		return ResponseEntity.ok(bookService.findAllBooks());
	}

	@PermitAll
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneBook(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.findBook(id));
	}

	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO) {
		return ResponseEntity.ok(bookService.addBook(addUpdateBookDTO));
	}
	@PreAuthorize(value = "hasAuthority('ADMIN')")
	@PutMapping("/{idBook}")
	public ResponseEntity<?> updateBook(@RequestBody AddUpdateBookDTO bookRequest, @PathVariable long idBook) {
		return ResponseEntity.ok(bookService.updateBook(bookRequest, idBook));
	}

	@PermitAll
	@GetMapping("/topSellingBooksLimit")
	public ResponseEntity<?> getTopSellingBooks() {
		return ResponseEntity.ok(bookService.getTopSellingBooks(topSellingBooksLimit));
	}
	@PermitAll
	@RequestMapping(value = "/getBooksFilter", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBooksFromCategories(@RequestParam(name = "id",required = false) Set<Long> id, @RequestParam("search") String search) {
		return ResponseEntity.ok(bookService.getBooksFilter(id,search));
	}

}
