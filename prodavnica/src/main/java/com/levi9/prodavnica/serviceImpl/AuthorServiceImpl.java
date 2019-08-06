package com.levi9.prodavnica.serviceImpl;

import com.levi9.prodavnica.model.Author;
import com.levi9.prodavnica.repository.AuthorRepository;
import com.levi9.prodavnica.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getOne(Long id) {
        return authorRepository.getOne(id);
    }
}
