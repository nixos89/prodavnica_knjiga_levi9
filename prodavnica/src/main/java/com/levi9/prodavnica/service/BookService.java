package com.levi9.prodavnica.service;

import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface BookService {

    public List<Book> findAllBooks();

    public Book findBook(Long id);



}
