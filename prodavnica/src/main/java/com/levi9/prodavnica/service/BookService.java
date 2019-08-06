package com.levi9.prodavnica.service;

import java.util.List;

import com.levi9.prodavnica.dto.UpdateBookDTO;
import com.levi9.prodavnica.model.Book;

public interface BookService {

	public List<Book> findAllBooks();

	public Book findBook(Long id);

	public boolean updateBook(UpdateBookDTO bookRequest, long idBook);

}
