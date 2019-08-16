package com.levi9.prodavnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.service.CategoryService;

@RestController
@RequestMapping("api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(categoryService.findAllCategories());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getOne(id));
	}

	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody AddCategoryDTO addCategoryDTO) {
		return new ResponseEntity<>(categoryService.addCategory(addCategoryDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody AddCategoryDTO addCategoryDTO, @PathVariable Long id) {
		return ResponseEntity.ok(categoryService.updateCategory(addCategoryDTO, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.deleteCategory(id));
	}

}
