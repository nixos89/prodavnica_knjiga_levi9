package com.levi9.prodavnica.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.model.Author;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Category;
import com.levi9.prodavnica.repository.AuthorRepository;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	BookMapper bookMapper;

	@Override
	public BookListDTO findAllBooks() {
		BookListDTO bookDTOS = new BookListDTO();
		List<Book> books = bookRepository.findAll().stream().filter(x -> x.isDeleted() == false)
				.collect(Collectors.toList());
		if (!books.isEmpty()) {
			for (Book book : books) {
				bookDTOS.getBooks().add(bookMapper.map(book));
			}
		} else
			throw new StoreException(HttpStatus.NOT_FOUND, "Book doesn't exist!");

		return bookDTOS;
	}

	@Override
	public BookDTO findBook(Long id) {
		Book book = bookRepository.getOne(id);
		if (book != null)
			return bookMapper.map(book);
		else
			throw new StoreException(HttpStatus.NOT_FOUND, "Book doesn't exist!");
	}

	@Override
	public boolean updateBook(AddUpdateBookDTO bookRequest, long idBook) {
		Book book = bookRepository.getOne(idBook);
		if (book != null) {

			book.setName(bookRequest.getName());
			book.setPrice(bookRequest.getPrice());
			book.setAmount(bookRequest.getAmount());
			book.setDeleted(bookRequest.isDeleted());

			Set<Author> bookAuthors = new HashSet<>();
			Set<Book> books = new HashSet<>();
			books.add(book);

			for (Long authorId : bookRequest.getAuthorIds()) {
				Author author = authorRepository.getOne(authorId);
				bookAuthors.add(author);
			}

			Set<Category> bookCategories = new HashSet<>();
			for (Long categoryId : bookRequest.getCategoryIds()) {
				Category category = categoryRepository.getOne(categoryId);
				bookCategories.add(category);
			}

			book.setAuthors(bookAuthors);
			book.setCategories(bookCategories);

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

		Set<Author> bookAuthors = new HashSet<>();
		Set<Book> books = new HashSet<>();
		books.add(book);

		for (Long authorId : addUpdateBookDTO.getAuthorIds()) {
			Author author = authorRepository.getOne(authorId);
			bookAuthors.add(author);
		}

		Set<Category> bookCategories = new HashSet<>();
		for (Long categoryId : addUpdateBookDTO.getCategoryIds()) {
			Category category = categoryRepository.getOne(categoryId);
			bookCategories.add(category);
		}

		book.setAuthors(bookAuthors);
		book.setCategories(bookCategories);

		bookRepository.save(book);

		return true;
	}

	@Override
	public BookListDTO searchForBook(String search) {
		BookListDTO bookListDTO = new BookListDTO();
		List<Book> books = bookRepository.getBookSearch(search);
		for (Book book : books)
			if (!book.isDeleted()
					&& bookListDTO.getBooks().stream().noneMatch(b -> b.getBookId().equals(book.getBookId())))
				bookListDTO.getBooks().add(bookMapper.map(book));
		return bookListDTO;
	}

}
