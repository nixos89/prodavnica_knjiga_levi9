package com.levi9.prodavnica.mapper;

import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO map(Author source);

}
