package com.levi9.prodavnica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.AuthorConstants;
import com.levi9.prodavnica.config.BookConstants;
import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.dto.TopSellingBookDTO;
import com.levi9.prodavnica.dto.TopSellingBookListDTO;
import com.levi9.prodavnica.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
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
				.andExpect(jsonPath("$.books.[1].deleted").value(BookConstants.book1deleted))
				.andDo(document("{class-name}/{method-name}", booksListResponseFields()));
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
				.andExpect(jsonPath("$.deleted").value(BookConstants.book0deleted))
				.andDo(document("{class-name}/{method-name}"));
	}

	@Test
	public void addBook() throws Exception {
		when(bookService.addBook(BookConstants.addUpdateDTO)).thenReturn(true);
		mockMvc.perform(post(UrlPrefix.GET_BOOKS).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(BookConstants.addUpdateDTO))).andExpect(status().isOk())
				.andDo(document("{class-name}/{method-name}"));
	}

	@Test
	public void updateBook() throws Exception {
		when(bookService.updateBook(BookConstants.addUpdateDTO, BookConstants.book0id)).thenReturn(true);
		mockMvc.perform(put(UrlPrefix.GET_BOOKS + "/" + BookConstants.book0id).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(BookConstants.addUpdateDTO))).andExpect(status().isOk())
				.andDo(document("{class-name}/{method-name}"));
	}

	@Test
	public void getBooksFilterTest() throws Exception{
		when(bookService.getBooksFilter(any(),any())).thenReturn(new BookListDTO(Lists.newArrayList(
				new BookDTO(BookConstants.book0id, BookConstants.book0name, BookConstants.book0price,
						BookConstants.book0amount, BookConstants.book0deleted),
				new BookDTO(BookConstants.book1id, BookConstants.book1name, BookConstants.book1price,
						BookConstants.book1amount, BookConstants.book1deleted))));
		mockMvc.perform(get(UrlPrefix.SEARCH_BOOKS).param("ids", "1").param("search","").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.books.[0].bookId").value(BookConstants.book0id))
				.andExpect(jsonPath("$.books.[0].name").value(BookConstants.book0name))
				.andExpect(jsonPath("$.books.[0].price").value(BookConstants.book0price))
				.andExpect(jsonPath("$.books.[0].amount").value(BookConstants.book0amount))
				.andExpect(jsonPath("$.books.[0].deleted").value(BookConstants.book0deleted))
				.andExpect(jsonPath("$.books.[1].bookId").value(BookConstants.book1id))
				.andExpect(jsonPath("$.books.[1].name").value(BookConstants.book1name))
				.andExpect(jsonPath("$.books.[1].price").value(BookConstants.book1price))
				.andExpect(jsonPath("$.books.[1].amount").value(BookConstants.book1amount))
				.andExpect(jsonPath("$.books.[1].deleted").value(BookConstants.book1deleted))
				.andDo(document("{class-name}/{method-name}",booksListResponseFields()));
	}


	private ResponseFieldsSnippet booksListResponseFields() {
		return responseFields(fieldWithPath("books.[].bookId").description("Id of the book"),
				fieldWithPath("books.[].name").description("Title of the book"),
				fieldWithPath("books.[].price").description("Price of the book"),
				fieldWithPath("books.[].amount").description("Amount of the book"),
				fieldWithPath("books.[].deleted").description("Status of the book"),
				fieldWithPath("books.[].authors").description("Authors of the book"),
				fieldWithPath("books.[].categories").description("Categories of the book"));
	}
	
	private ResponseFieldsSnippet topSellingBooksListResponseFields() {
		return responseFields(
				fieldWithPath("topSellingBookList.[].bookName").description("Title/name of first top selling book"),
				fieldWithPath("topSellingBookList.[].authors.[].authorId").description("Id of first book author"),
				fieldWithPath("topSellingBookList.[].authors.[].firstName").description("First name of first book author"),
				fieldWithPath("topSellingBookList.[].authors.[].lastName").description("Last name of first book author"),
				fieldWithPath("topSellingBookList.[].amount").description("Amount of first top selling book"));			
	}
	
	@Test
	public void getTopSellingBooks() throws Exception {
		Set<AuthorDTO> authorsBook0DTO = new LinkedHashSet<AuthorDTO>(); 
		authorsBook0DTO.add(new AuthorDTO(AuthorConstants.JOVA_ID, AuthorConstants.FIRST_NAME_JOVA, AuthorConstants.LAST_NAME_JOVA));
		authorsBook0DTO.add(new AuthorDTO(AuthorConstants.PERA_ID, AuthorConstants.FIRST_NAME_PERA, AuthorConstants.LAST_NAME_PERA));
		
		
		Set<AuthorDTO> authorsBook1DTO = new LinkedHashSet<AuthorDTO>(); 
		authorsBook1DTO.add(new AuthorDTO(AuthorConstants.DESA_ID, AuthorConstants.FIRST_NAME_DESA, AuthorConstants.LAST_NAME_DESA));
		authorsBook1DTO.add(new AuthorDTO(AuthorConstants.PERA_ID, AuthorConstants.FIRST_NAME_PERA, AuthorConstants.LAST_NAME_PERA));
		
		
		when(bookService.getTopSellingBooks(5)).thenReturn(new TopSellingBookListDTO(Lists.newArrayList(
					new TopSellingBookDTO(BookConstants.book0name, 
							authorsBook0DTO,
							BookConstants.book0amount
						)
					, new TopSellingBookDTO(BookConstants.book1name, 
							authorsBook1DTO,
							BookConstants.book1amount
						)				
				)
		));
		
		
		mockMvc.perform(get(UrlPrefix.GET_TOP_SELLING_BOOKS).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
			.andExpect(jsonPath("$.topSellingBookList.[0].bookName").value(BookConstants.book0name))									
			.andExpect(jsonPath("$.topSellingBookList.[0].authors.[0].authorId").value(AuthorConstants.JOVA_ID))
			.andExpect(jsonPath("$.topSellingBookList.[0].authors.[0].firstName").value(AuthorConstants.FIRST_NAME_JOVA))
			.andExpect(jsonPath("$.topSellingBookList.[0].authors.[0].lastName").value(AuthorConstants.LAST_NAME_JOVA))
			.andExpect(jsonPath("$.topSellingBookList.[0].authors.[1].authorId").value(AuthorConstants.PERA_ID))
			.andExpect(jsonPath("$.topSellingBookList.[0].authors.[1].firstName").value(AuthorConstants.FIRST_NAME_PERA))
			.andExpect(jsonPath("$.topSellingBookList.[0].authors.[1].lastName").value(AuthorConstants.LAST_NAME_PERA))
			.andExpect(jsonPath("$.topSellingBookList.[0].amount").value(BookConstants.book0amount))
			
			.andExpect(jsonPath("$.topSellingBookList.[1].bookName").value(BookConstants.book1name))
			.andExpect(jsonPath("$.topSellingBookList.[1].authors.[0].authorId").value(AuthorConstants.DESA_ID))
			.andExpect(jsonPath("$.topSellingBookList.[1].authors.[0].firstName").value(AuthorConstants.FIRST_NAME_DESA))
			.andExpect(jsonPath("$.topSellingBookList.[1].authors.[0].lastName").value(AuthorConstants.LAST_NAME_DESA))			
			.andExpect(jsonPath("$.topSellingBookList.[1].authors.[1].authorId").value(AuthorConstants.PERA_ID))
			.andExpect(jsonPath("$.topSellingBookList.[1].authors.[1].firstName").value(AuthorConstants.FIRST_NAME_PERA))
			.andExpect(jsonPath("$.topSellingBookList.[1].authors.[1].lastName").value(AuthorConstants.LAST_NAME_PERA))			
			.andExpect(jsonPath("$.topSellingBookList.[1].amount").value(BookConstants.book1amount))
			.andDo(document("{class-name}/{method-name}", topSellingBooksListResponseFields()));;
			
			
		
	}// getTopSellingBooks
	
	

}
