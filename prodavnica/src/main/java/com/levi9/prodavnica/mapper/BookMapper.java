package com.levi9.prodavnica.mapper;

import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class, AuthorMapper.class })
public interface BookMapper {
	BookDTO map(Book book);

	Book map(BookDTO source);

}
