package com.levi9.prodavnica.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book findBook(Long id) {
		return bookRepository.getOne(id);
	}

	@Override
	public boolean updateBook(AddUpdateBookDTO bookRequest, long idBook) {
		Book book = bookRepository.getOne(idBook);
		if (book != null) {

			book.setName(bookRequest.getName());
			book.setPrice(bookRequest.getPrice());
			book.setAmount(bookRequest.getAmount());
			book.setDeleted(bookRequest.isDeleted());

			bookRepository.save(book);

			return true;
		} else {
			throw new StoreException(HttpStatus.NOT_FOUND, "Book doesn't exist!");
		}

	}

	@Override
	public boolean addBook(AddUpdateBookDTO addUpdateBookDTO) {
		Book book = new Book();
		book.setAmount(addUpdateBookDTO.getAmount());
		book.setDeleted(addUpdateBookDTO.isDeleted());
		book.setName(addUpdateBookDTO.getName());
		book.setPrice(addUpdateBookDTO.getPrice());

		bookRepository.save(book);

		return true;
	}
}
