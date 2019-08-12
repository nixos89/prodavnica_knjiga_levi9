package com.levi9.prodavnica.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.levi9.prodavnica.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.dto.BookDTO;
import com.levi9.prodavnica.dto.BookListDTO;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.dto.CategoryListDTO;
import com.levi9.prodavnica.exception.StoreException;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.mapper.CategoryMapper;
import com.levi9.prodavnica.model.Category;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	BookMapper bookMapper;

	@Override
	public CategoryListDTO findAllCategories() {
		CategoryListDTO categoryListDTO = new CategoryListDTO();
		List<Category> categories = categoryRepository.findAll();
		if (!categories.isEmpty()) {
			for (Category category : categories) {
				if(!category.isDeleted())
				categoryListDTO.getCategories().add(categoryMapper.map(category));
			}
		} else
			throw new StoreException(HttpStatus.NOT_FOUND, "Category doesn't exist!");
		return categoryListDTO;
	}

	@Override
	public CategoryDTO getOne(Long id) {
		return categoryMapper.map(categoryRepository.getOne(id));
	}

	@Override
	public Boolean addCategory(AddCategoryDTO addCategoryDTO) {
		Category category = new Category();
		category.setName(addCategoryDTO.getName());
		category.setDeleted(addCategoryDTO.getIsDeleted());
		categoryRepository.save(category);
		return true;
	}

	@Override
	public Boolean updateCategory(AddCategoryDTO addCategoryDTO, Long id) {
		Category category = categoryRepository.getOne(id);
		if (category == null)
			throw new StoreException(HttpStatus.NOT_FOUND, "Category doesn't exist!");

		category.setName(addCategoryDTO.getName());
		category.setDeleted(addCategoryDTO.getIsDeleted());
		categoryRepository.save(category);
		return true;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		Category category = categoryRepository.getOne(id);
		if (category == null)
			throw new StoreException(HttpStatus.NOT_FOUND, "Category doesn't exist!");

		Set<Book> books = category.getBooks();
		if(!books.isEmpty()){
			return false;
		}


		category.setDeleted(true);
		return true;
	}

	@Override
	public BookListDTO getAllBooksFromCategories(Set<Long> ids) {
		List<BookDTO> books = new ArrayList<>();
		for (Long idCategory : ids) {
			if (!categoryRepository.getBooksFromCategories(idCategory).isEmpty()) {
				for (Long idBook : categoryRepository.getBooksFromCategories(idCategory)) {
					BookDTO book = bookMapper.map(bookRepository.getOne(idBook));
					if (!book.isDeleted())
						books.add(book);
					else
						throw new StoreException(HttpStatus.NOT_FOUND, "Book status is disabled!");
				}
			} else
				throw new StoreException(HttpStatus.NOT_FOUND, "Book doesn't exist!");
		}

		return new BookListDTO(books);
	}

}
