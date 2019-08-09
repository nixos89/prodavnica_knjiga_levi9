package com.levi9.prodavnica.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.AuthorListDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.mapper.AuthorMapper;
import com.levi9.prodavnica.model.Author;
import com.levi9.prodavnica.repository.AuthorRepository;
import com.levi9.prodavnica.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	AuthorMapper authorMapper;

	@Override
	public AuthorListDTO findAllAuthors() {
		AuthorListDTO authorListDTO = new AuthorListDTO();
		List<Author> authors = authorRepository.findAll();
		for (Author author : authors) {
			authorListDTO.getAuthors().add(authorMapper.map(author));
		}
		return authorListDTO;
	}

	@Override
	public AuthorDTO getOne(Long id) {
		Author author = authorRepository.getOne(id);
		if (author != null)
			return authorMapper.map(author);
		else
			throw new StoreException(HttpStatus.NOT_FOUND, "Author doesn't exist!");
	}
}
