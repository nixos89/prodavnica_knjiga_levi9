package com.levi9.prodavnica.service;

import java.util.List;
import java.util.Set;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.dto.TopSellingBookListDTO;

public interface BookService {

	public BookListDTO findAllBooks();

	public BookDTO findBook(Long id);

	public boolean updateBook(AddUpdateBookDTO bookRequest, long idBook);

	boolean addBook(AddUpdateBookDTO addUpdateBookDTO);
	
	public BookListDTO getTop10SellingBooks();
	
	public TopSellingBookListDTO getTopSellingBooks(int topSellingBooksLimit); //  use this one
}
