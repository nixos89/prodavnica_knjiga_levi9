package com.levi9.prodavnica.service;

import java.util.List;

import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.AuthorListDTO;
import com.levi9.prodavnica.model.Author;

public interface AuthorService {

	public AuthorListDTO findAllAuthors();

	public AuthorDTO getOne(Long id);

}
