package com.levi9.prodavnica.service;

import com.levi9.prodavnica.config.BookConstants;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.repository.AuthorRepository;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.repository.OrderItemRepository;
import com.levi9.prodavnica.serviceImpl.BookServiceImpl;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Autowired
    BookServiceImpl bookService;

    @Test
    public void getFilterSearch_returnSuccess() throws Exception{
        when(bookRepository.getBooksFilterSearch(any())).thenReturn(Lists.newArrayList(1L,2L));
        when(bookRepository.getOne(any())).thenReturn(new Book(BookConstants.book0id,BookConstants.book0name,BookConstants.book0price,BookConstants.book0amount,BookConstants.book0deleted));
        when(bookMapper.map(any(Book.class))).thenReturn(new BookDTO(BookConstants.book0id,BookConstants.book0name,BookConstants.book0price,BookConstants.book0amount,BookConstants.book0deleted));

        BookListDTO bookListDTO = bookService.getBooksFilter(null,any());
        verify(bookRepository).getBooksFilterSearch(any());

        assertEquals(bookListDTO.getBooks().get(0).getBookId(),BookConstants.book0id);
        assertEquals(bookListDTO.getBooks().get(0).getName(),BookConstants.book0name);
    }

    @Test
    public void getFilterAll_returnSuccess() throws Exception{
        when(bookRepository.getBooksFilterAll(any(),any())).thenReturn(Lists.newArrayList(1L,2L));
        when(bookRepository.getOne(any())).thenReturn(new Book(BookConstants.book0id,BookConstants.book0name,BookConstants.book0price,BookConstants.book0amount,BookConstants.book0deleted));
        when(bookMapper.map(any(Book.class))).thenReturn(new BookDTO(BookConstants.book0id,BookConstants.book0name,BookConstants.book0price,BookConstants.book0amount,BookConstants.book0deleted));

        BookListDTO bookListDTO = bookService.getBooksFilter(Sets.newHashSet(),"");
        verify(bookRepository).getBooksFilterAll(any(),any());

        assertEquals(bookListDTO.getBooks().get(0).getBookId(),BookConstants.book0id);
        assertEquals(bookListDTO.getBooks().get(0).getName(),BookConstants.book0name);
    }



}
