package com.levi9.prodavnica.controller;

import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.dto.CategoryListDTO;
import com.levi9.prodavnica.model.Category;
import com.levi9.prodavnica.repository.CategoryRepository;
import com.levi9.prodavnica.service.CategoryService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @Test
    public void findAllCategories() throws Exception{
        when(categoryService.findAllCategories()).thenReturn(new CategoryListDTO(Lists.newArrayList(new CategoryDTO(1L,"asd",false))));
        mockMvc.perform(get(UrlPrefix.GET_CATEGORIES).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$.categories.[0].name").value("asd"));

    }

}
