package com.levi9.prodavnica.serviceImpl;

import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.AuthorListDTO;
import com.levi9.prodavnica.mapper.AuthorMapper;
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

    @Autowired
    AuthorMapper authorMapper;

    @Override
    public AuthorListDTO findAllAuthors() {
        AuthorListDTO authorListDTO = new AuthorListDTO();
        List<Author> authors = authorRepository.findAll();
        for(Author author : authors){
            authorListDTO.getAuthors().add(authorMapper.map(author));
        }
        return authorListDTO;
    }

    @Override
    public AuthorDTO getOne(Long id) {
        Author author = authorRepository.getOne(id);
        return authorMapper.map(author);
    }
}
