package com.levi9.prodavnica.controller;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		return ResponseEntity.ok(categoryService.addCategory(addCategoryDTO));
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
