package com.levi9.prodavnica.service;

import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.AuthorListDTO;

public interface AuthorService {

	public AuthorListDTO findAllAuthors();

	public AuthorDTO getOne(Long id);

}
