package com.levi9.prodavnica.service;

import java.util.Set;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;

public interface BookService {

	public BookListDTO findAllBooks();

	public BookDTO findBook(Long id);

	public boolean updateBook(AddUpdateBookDTO bookRequest, long idBook);

	boolean addBook(AddUpdateBookDTO addUpdateBookDTO);
	
	BookListDTO getAllBooksFromCategories(Set<Long> id);
}
