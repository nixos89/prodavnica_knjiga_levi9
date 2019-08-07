package com.levi9.prodavnica.mapper;

import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO map(Category category);
}
