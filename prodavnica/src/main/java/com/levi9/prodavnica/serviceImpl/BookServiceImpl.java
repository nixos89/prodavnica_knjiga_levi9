package com.levi9.prodavnica.serviceImpl;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.HashMap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.dto.TopSellingBookDTO;
import com.levi9.prodavnica.dto.TopSellingBookListDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.model.Author;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Category;
import com.levi9.prodavnica.repository.AuthorRepository;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.repository.OrderItemRepository;
import com.levi9.prodavnica.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	BookRepository bookRepository;

	AuthorRepository authorRepository;
	
	CategoryRepository categoryRepository;

	OrderItemRepository orderItemRepository;

	BookMapper bookMapper;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
			CategoryRepository categoryRepository, OrderItemRepository orderItemRepository, BookMapper bookMapper) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.categoryRepository = categoryRepository;
		this.orderItemRepository = orderItemRepository;
		this.bookMapper = bookMapper;
	}

	@Override
	public BookListDTO findAllBooks() {
		BookListDTO bookDTOS = new BookListDTO();
		List<Book> books = bookRepository.getAllBooks();
		if (!books.isEmpty()) {
			for (Book book : books) {
				bookDTOS.getBooks().add(bookMapper.map(book));
			}
		} else
			return new BookListDTO();

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
	public TopSellingBookListDTO getTopSellingBooks(int topSellingBooksLimit) {		
		Map<Long, Long> topSellingBooksMap = new HashMap<>();
		LinkedList<TopSellingBookDTO> topSellingBooksList = new LinkedList<TopSellingBookDTO>();
		
		orderItemRepository.getTopSellingBooks().stream()
			.limit(topSellingBooksLimit)
			.forEach(obj -> topSellingBooksMap.put(obj.getBookId(), obj.getSoldAmount()));
				
		for (Entry<Long, Long> topBook : topSellingBooksMap.entrySet()) {						
			
			Book book = bookRepository.getOne(topBook.getKey());
			if (book != null) {
				Set<AuthorDTO> authorDTOSet = new HashSet<>();
				for(Author a: book.getAuthors()) {
					AuthorDTO authorDTO = new AuthorDTO();
					authorDTO.setAuthorId(a.getAuthorId());
					authorDTO.setFirstName(a.getFirstName());
					authorDTO.setLastName(a.getLastName());
					authorDTOSet.add(authorDTO);
				}
									
				TopSellingBookDTO topSellingBookDTO = new TopSellingBookDTO(book.getName(), authorDTOSet,
						 (int)(long)topBook.getValue());
				topSellingBooksList.add(topSellingBookDTO);
			} else {
				throw new StoreException(HttpStatus.NOT_FOUND, "No book has been found in TOP selling books!");
			}			
		}

		TopSellingBookListDTO topSellingBookListDTO = new TopSellingBookListDTO();
		topSellingBookListDTO.setTopSellingBookList(
				topSellingBooksList.stream().sorted(Comparator.comparingInt(TopSellingBookDTO::getAmount).reversed()).collect(Collectors.toList()));		
		
		return topSellingBookListDTO;
	}
	
	@Override
	public BookListDTO getBooksFilter(Set<Long> ids, String search) {
		List<BookDTO> books = new ArrayList<>();
		List<Long> booksId = new ArrayList<>();

		 booksId = ids == null ? bookRepository.getBooksFilterSearch(search) : bookRepository.getBooksFilterAll(ids,search);

		 if (!booksId.isEmpty()) {
			for (Long idBook : booksId) {
				BookDTO book = bookMapper.map(bookRepository.getOne(idBook));
				if (!books.stream().anyMatch(x -> x.getBookId() == idBook))
					books.add(book);
			}
		} else
			return new BookListDTO();

		return new BookListDTO(books);
	}


}
