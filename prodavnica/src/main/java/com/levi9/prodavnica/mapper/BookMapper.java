package com.levi9.prodavnica.mapper;

import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.model.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class,AuthorMapper.class})
@Component
public interface BookMapper {
    BookDTO map(Book book);

    Book map(BookDTO source);

}
