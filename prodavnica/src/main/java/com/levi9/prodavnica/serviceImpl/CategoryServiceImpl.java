package com.levi9.prodavnica.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

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

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

}
