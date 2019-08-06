package com.levi9.prodavnica.controller;

import com.levi9.prodavnica.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

//    @Autowired
//    public BookController(BookService bookService){
//        this.bookService = bookService;
//    }

    public ResponseEntity<?> getAllBooks(){

        return null;
    }


}
