package com.levi9.prodavnica.controller;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PermitAll
	@GetMapping
	public ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(categoryService.findAllCategories());
	}

	@PermitAll
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getOne(id));
	}

	@PreAuthorize(value = "hasAuthority('ADMIN') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody @Validated AddCategoryDTO addCategoryDTO) {
		return new ResponseEntity<>(categoryService.addCategory(addCategoryDTO), HttpStatus.CREATED);
	}

	@PreAuthorize(value = "hasAuthority('ADMIN') or hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody @Validated AddCategoryDTO addCategoryDTO, @PathVariable Long id) {
		return ResponseEntity.ok(categoryService.updateCategory(addCategoryDTO, id));
	}

	@PreAuthorize(value = "hasAuthority('ADMIN') or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.deleteCategory(id));
	}

}
