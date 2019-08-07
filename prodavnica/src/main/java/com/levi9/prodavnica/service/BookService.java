package com.levi9.prodavnica.service;

import java.util.List;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.model.Book;

public interface BookService {

	public List<BookDTO> findAllBooks();

	public BookDTO findBook(Long id);

	public boolean updateBook(AddUpdateBookDTO bookRequest, long idBook);

	boolean addBook(AddUpdateBookDTO addUpdateBookDTO);
}
