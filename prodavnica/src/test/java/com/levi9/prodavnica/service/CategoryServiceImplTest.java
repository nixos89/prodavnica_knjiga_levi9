package com.levi9.prodavnica.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.CategoryConstants;
import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.dto.CategoryListDTO;
import com.levi9.prodavnica.mapper.BookMapper;
import com.levi9.prodavnica.mapper.CategoryMapper;
import com.levi9.prodavnica.model.Category;
import com.levi9.prodavnica.repository.BookRepository;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.serviceImpl.CategoryServiceImpl;
import com.levi9.prodavnica.serviceImpl.CustomUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryServiceImpl.class)
public class CategoryServiceImplTest {

    @MockBean
    CustomUserDetailsService userDetailsService;


    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private CategoryMapper categoryMapper;
    @MockBean
    private BookMapper bookMapper;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findAllCategories_returnSuccess() throws Exception{
        when(categoryRepository.findAll()).thenReturn(CategoryConstants.createListCategory());
        when(categoryMapper.map(any())).thenReturn(new CategoryDTO(CategoryConstants.category0id,CategoryConstants.category0name,CategoryConstants.category0isDeleted));

        CategoryListDTO categoryListDTO = categoryService.findAllCategories();
        verify(categoryRepository).findAll();

       assertEquals(categoryListDTO.getCategories().get(0).getCategoryId(),CategoryConstants.category0id);
       assertEquals(categoryListDTO.getCategories().get(0).getName(),CategoryConstants.category0name);
    }

    @Test
    public void findOneCategoriy_returnSuccess() throws Exception{
        when(categoryRepository.getOne(any())).thenReturn(CategoryConstants.createCategory());
        when(categoryMapper.map(any())).thenReturn(new CategoryDTO(CategoryConstants.category0id,CategoryConstants.category0name,CategoryConstants.category0isDeleted));

        CategoryDTO categoryDTO = categoryService.getOne(any());
        verify(categoryRepository).getOne(any());
        assertEquals(categoryDTO.getCategoryId(),CategoryConstants.category0id);
        assertEquals(categoryDTO.getName(),CategoryConstants.category0name);

    }

    @Test
    public void addCategoy_returnSuccess() throws Exception{
        Category category = CategoryConstants.createCategory();
        when(categoryRepository.save(any())).thenReturn(category);
        when(categoryMapper.map(category)).thenReturn(CategoryConstants.create());

        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        CategoryDTO categoryDTO = categoryService.addCategory(new AddCategoryDTO(CategoryConstants.category0name,CategoryConstants.category0isDeleted));
        verify(categoryRepository).save(captor.capture());

        Category categoryRequestActual = captor.getValue();
        assertEquals(categoryDTO.getName(),categoryRequestActual.getName());
        assertEquals(CategoryConstants.category0id,categoryDTO.getCategoryId());
    }



}
