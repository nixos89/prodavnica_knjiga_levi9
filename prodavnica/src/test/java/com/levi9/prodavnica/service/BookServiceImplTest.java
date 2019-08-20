package com.levi9.prodavnica.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.levi9.prodavnica.config.AuthorConstants;
import com.levi9.prodavnica.config.BookConstants;
import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.dto.TopSellingBookDTO;
import com.levi9.prodavnica.dto.TopSellingBookListDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.model.Author;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.repository.AuthorRepository;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.repository.OrderItemRepository;
import com.levi9.prodavnica.serviceImpl.BookServiceImpl;
import com.levi9.prodavnica.utils.SalesDetails;

@RunWith(SpringRunner.class)
@WebMvcTest(BookServiceImpl.class)
public class BookServiceImplTest {

	@MockBean
	BookRepository bookRepository;
	@MockBean
	AuthorRepository authorRepository;
	@MockBean
	CategoryRepository categoryRepository;
	@MockBean
	BookMapper bookMapper;

	@MockBean
	OrderItemRepository orderItemRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	BookServiceImpl bookService;
	
	@Before
	public void setUp() {
		this.bookService = new BookServiceImpl(bookRepository,authorRepository,categoryRepository,orderItemRepository,bookMapper);
	}

	@Test
	public void getFilterSearch_returnSuccess() throws Exception {
		when(bookRepository.getBooksFilterSearch(any())).thenReturn(Lists.newArrayList(1L, 2L));
		when(bookRepository.getOne(any())).thenReturn(new Book(BookConstants.book0id, BookConstants.book0name,
				BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted));
		when(bookMapper.map(any(Book.class))).thenReturn(new BookDTO(BookConstants.book0id, BookConstants.book0name,
				BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted));

		BookListDTO bookListDTO = bookService.getBooksFilter(null, any());
		verify(bookRepository).getBooksFilterSearch(any());

		assertEquals(bookListDTO.getBooks().get(0).getBookId(), BookConstants.book0id);
		assertEquals(bookListDTO.getBooks().get(0).getName(), BookConstants.book0name);
	}

	@Test
	public void getFilterAll_returnSuccess() throws Exception {
		when(bookRepository.getBooksFilterAll(any(), any())).thenReturn(Lists.newArrayList(1L, 2L));
		when(bookRepository.getOne(any())).thenReturn(new Book(BookConstants.book0id, BookConstants.book0name,
				BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted));
		when(bookMapper.map(any(Book.class))).thenReturn(new BookDTO(BookConstants.book0id, BookConstants.book0name,
				BookConstants.book0price, BookConstants.book0amount, BookConstants.book0deleted));

		BookListDTO bookListDTO = bookService.getBooksFilter(Sets.newHashSet(), "");
		verify(bookRepository).getBooksFilterAll(any(), any());

		assertEquals(bookListDTO.getBooks().get(0).getBookId(), BookConstants.book0id);
		assertEquals(bookListDTO.getBooks().get(0).getName(), BookConstants.book0name);
	}

	@Test
	public void getTopSellingBooks_returnSuccess() throws Exception {

		Map<Long, Long> topSellingBooksMap = new LinkedHashMap<Long, Long>();
		topSellingBooksMap.put(BookConstants.book0id, (long) BookConstants.book0amount);
		topSellingBooksMap.put(BookConstants.book1id, (long) BookConstants.book1amount);
		List<SalesDetails> salesDetailsList = new LinkedList<SalesDetails>();

		for (Entry<Long, Long> entry : topSellingBooksMap.entrySet()) {
			salesDetailsList.add(new SalesDetails(entry.getKey(), entry.getValue()));
		}
		
		when(orderItemRepository.getTopSellingBooks()).thenReturn(salesDetailsList);		
		
		Book book1 = new Book(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price, 
				BookConstants.book0amount, BookConstants.book0deleted);
		Book book2 = new Book(BookConstants.book1id, BookConstants.book1name, BookConstants.book1price, 
				BookConstants.book1amount, BookConstants.book1deleted);
		
		when(bookRepository.getOne(1L)).thenReturn(book1);
		when(bookRepository.getOne(2L)).thenReturn(book2);
		
		Set<Author> authorSetBook1 = new LinkedHashSet<>();
		authorSetBook1.add(AuthorConstants.authors().get(0));
		authorSetBook1.add(AuthorConstants.authors().get(1));
		book1.setAuthors(authorSetBook1);
		
		Set<Author> authorSetBook2 = new LinkedHashSet<>();
		authorSetBook2.add(AuthorConstants.authors().get(2));
		authorSetBook2.add(AuthorConstants.authors().get(1));
		book2.setAuthors(authorSetBook2);						
		
		Set<AuthorDTO> authorDTOSetBook0Mock = new LinkedHashSet<AuthorDTO>();
		Set<AuthorDTO> authorDTOSetBook1Mock = new LinkedHashSet<AuthorDTO>();		 
		
		authorDTOSetBook0Mock.add(AuthorConstants.authorDTOs().get(0));
		authorDTOSetBook0Mock.add(AuthorConstants.authorDTOs().get(1));
		
		authorDTOSetBook1Mock.add(AuthorConstants.authorDTOs().get(2));
		authorDTOSetBook1Mock.add(AuthorConstants.authorDTOs().get(1));
		
		TopSellingBookDTO topSellingBook0DTO = new TopSellingBookDTO(BookConstants.book0name, 
				authorDTOSetBook0Mock, BookConstants.book0amount);
		
		TopSellingBookDTO topSellingBook1DTO = new TopSellingBookDTO(BookConstants.book1name, 
				authorDTOSetBook1Mock, BookConstants.book1amount);
		
		
		LinkedList<TopSellingBookDTO> topSellingBookDTOs = new LinkedList<>();
		topSellingBookDTOs.add(topSellingBook0DTO);
		topSellingBookDTOs.add(topSellingBook1DTO);
		
		TopSellingBookListDTO topSellingBookListDTOMock = new TopSellingBookListDTO();
		topSellingBookListDTOMock.setTopSellingBookList(topSellingBookDTOs);		
		
		int listSize = 2;
		int limit = 2;
		
		topSellingBookListDTOMock = bookService.getTopSellingBooks(limit);			
		assertEquals(topSellingBookListDTOMock.getTopSellingBookList().size(), listSize);		
	}

	@Test
	public void getTopSellingBooks_throwsException() throws Exception {
		int limit = 2;

		Map<Long, Long> topSellingBooksMap = new LinkedHashMap<Long, Long>();
		topSellingBooksMap.put(BookConstants.book0id, (long) BookConstants.book0amount);
		topSellingBooksMap.put(BookConstants.book1id, (long) BookConstants.book1amount);
		
		List<SalesDetails> salesDetailsList = new LinkedList<SalesDetails>();		
		
		for (Entry<Long, Long> entry : topSellingBooksMap.entrySet()) {
			salesDetailsList.add(new SalesDetails(entry.getKey(), entry.getValue()));
		}

		when(orderItemRepository.getTopSellingBooks()).thenReturn(salesDetailsList);
				
		Map<Long, Long> newTopSellingBooksMap = new LinkedHashMap<Long, Long>();
		salesDetailsList.stream()
			.limit(limit)
			.forEach(obj -> newTopSellingBooksMap.put(obj.getBookId(), obj.getSoldAmount()));				
				
		thrown.expect(StoreException.class);
		
		bookService.getTopSellingBooks(limit);
	}

}
