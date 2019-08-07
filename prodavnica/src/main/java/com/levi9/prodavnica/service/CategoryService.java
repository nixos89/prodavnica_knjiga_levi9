package com.levi9.prodavnica.service;

import java.util.List;

import com.levi9.prodavnica.dto.CategoryListDTO;
import com.levi9.prodavnica.model.Category;

public interface CategoryService {

	CategoryListDTO findAllCategories();

}
