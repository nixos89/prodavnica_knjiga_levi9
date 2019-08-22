package com.levi9.prodavnica.mapper;

import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO map(Category category);
}
