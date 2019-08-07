package com.levi9.prodavnica.mapper;

import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO map(Author source);

}
