package com.levi9.prodavnica.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.dto.CategoryListDTO;
import com.levi9.prodavnica.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi9.prodavnica.model.Category;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public CategoryListDTO findAllCategories() {
		CategoryListDTO categoryListDTO = new CategoryListDTO();
		List<Category> categories = categoryRepository.findAll();
		if(!categories.isEmpty()){
			for(Category category: categories){
				categoryListDTO.getCategories().add(categoryMapper.map(category));
			}
		}
		return categoryListDTO;
	}

	@Override
	public CategoryDTO getOne(Long id) {
		return categoryMapper.map(categoryRepository.getOne(id));
	}

	@Override
	public Boolean addCategory(AddCategoryDTO addCategoryDTO) {
		Category category  = new Category();
		category.setName(addCategoryDTO.getName());
		category.setDeleted(addCategoryDTO.getIsDeleted());
		categoryRepository.save(category);
		return true;
	}

	@Override
	public Boolean updateCategory(AddCategoryDTO addCategoryDTO, Long id) {
		Category category = categoryRepository.getOne(id);
		if(category == null)
			return null;

		category.setName(addCategoryDTO.getName());
		category.setDeleted(addCategoryDTO.getIsDeleted());
		categoryRepository.save(category);
		return true;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		Category category = categoryRepository.getOne(id);
		if(category==null)
			return false;

		category.setDeleted(true);
		return true;
	}


}
