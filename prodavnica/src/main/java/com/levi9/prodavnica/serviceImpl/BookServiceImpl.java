package com.levi9.prodavnica.serviceImpl;

import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
