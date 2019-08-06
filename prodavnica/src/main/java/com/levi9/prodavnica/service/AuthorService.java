package com.levi9.prodavnica.service;

import com.levi9.prodavnica.model.Author;
import com.levi9.prodavnica.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    public List<Author> findAllAuthors();

    public Author getOne(Long id);


}
