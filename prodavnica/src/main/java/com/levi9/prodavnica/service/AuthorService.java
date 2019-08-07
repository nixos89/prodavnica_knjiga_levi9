package com.levi9.prodavnica.service;

import java.util.List;

import com.levi9.prodavnica.model.Author;

public interface AuthorService {

	public List<Author> findAllAuthors();

	public Author getOne(Long id);

}
