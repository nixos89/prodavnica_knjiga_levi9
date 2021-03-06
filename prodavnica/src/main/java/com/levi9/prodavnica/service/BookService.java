package com.levi9.prodavnica.service;

import java.security.Principal;
import java.util.Set;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.dto.TopSellingBookListDTO;

public interface BookService {

	public BookListDTO findAllBooks(Principal principal);

	public BookDTO findBook(Long id);

	public boolean updateBook(AddUpdateBookDTO bookRequest, long idBook);

	boolean addBook(AddUpdateBookDTO addUpdateBookDTO);
	
	BookListDTO getBooksFilter(Set<Long> id, String search);
	
	public TopSellingBookListDTO getTopSellingBooks(int topSellingBooksLimit);
}
