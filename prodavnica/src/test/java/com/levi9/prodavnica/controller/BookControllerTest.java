package com.levi9.prodavnica.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.BookConstants;
import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.AddUpdateBookDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	BookService bookService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void findAllBooks() throws Exception {
		when(bookService.findAllBooks()).thenReturn(new BookListDTO(Lists.newArrayList(
				new BookDTO(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price,
						BookConstants.book0amount, BookConstants.book0deleted),
				new BookDTO(BookConstants.book1id, BookConstants.book1name, BookConstants.book1price,
						BookConstants.book1amount, BookConstants.book1deleted))));
		mockMvc.perform(get(UrlPrefix.GET_BOOKS).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.books.[0].bookId").value(BookConstants.book0id))
				.andExpect(jsonPath("$.books.[0].name").value(BookConstants.book0name))
				.andExpect(jsonPath("$.books.[0].price").value(BookConstants.book0price))
				.andExpect(jsonPath("$.books.[0].amount").value(BookConstants.book0amount))
				.andExpect(jsonPath("$.books.[0].deleted").value(BookConstants.book0deleted))
				.andExpect(jsonPath("$.books.[1].bookId").value(BookConstants.book1id))
				.andExpect(jsonPath("$.books.[1].name").value(BookConstants.book1name))
				.andExpect(jsonPath("$.books.[1].price").value(BookConstants.book1price))
				.andExpect(jsonPath("$.books.[1].amount").value(BookConstants.book1amount))
				.andExpect(jsonPath("$.books.[1].deleted").value(BookConstants.book1deleted));
	}

	@Test
	public void findOneBook() throws Exception {
		when(bookService.findBook(BookConstants.book0id))
				.thenReturn(new BookDTO(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price,
						BookConstants.book0amount, BookConstants.book0deleted));
		mockMvc.perform(get(UrlPrefix.GET_BOOKS + "/" + BookConstants.book0id).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.bookId").value(BookConstants.book0id))
				.andExpect(jsonPath("$.name").value(BookConstants.book0name))
				.andExpect(jsonPath("$.price").value(BookConstants.book0price))
				.andExpect(jsonPath("$.amount").value(BookConstants.book0amount))
				.andExpect(jsonPath("$.deleted").value(BookConstants.book0deleted));
	}

	@Test
	public void addBook() throws Exception {
		AddUpdateBookDTO addDTO = new AddUpdateBookDTO(BookConstants.book0name, BookConstants.book0price,
				BookConstants.book0amount, BookConstants.book0deleted, BookConstants.book0authors,
				BookConstants.book0categories);

		when(bookService.addBook(addDTO)).thenReturn(true);
		mockMvc.perform(post(UrlPrefix.GET_BOOKS).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(addDTO)))
				.andExpect(status().isOk());
	}

	@Test
	public void updateBook() throws Exception {
		AddUpdateBookDTO updateDTO = new AddUpdateBookDTO(BookConstants.book0name, BookConstants.book0price,
				BookConstants.book0amount, BookConstants.book0deleted, BookConstants.book0authors,
				BookConstants.book0categories);

		when(bookService.updateBook(updateDTO, BookConstants.book0id)).thenReturn(true);
		mockMvc.perform(put(UrlPrefix.GET_BOOKS + "/" + BookConstants.book0id).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updateDTO)))
				.andExpect(status().isOk());
	}

}
