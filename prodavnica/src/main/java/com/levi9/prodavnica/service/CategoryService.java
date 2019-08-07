package com.levi9.prodavnica.service;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.dto.CategoryListDTO;

public interface CategoryService {

	CategoryListDTO findAllCategories();

	CategoryDTO getOne(Long id);

	Boolean addCategory(AddCategoryDTO addCategoryDTO);

	Boolean updateCategory(AddCategoryDTO addCategoryDTO, Long id);

	Boolean deleteCategory(Long id);
}
