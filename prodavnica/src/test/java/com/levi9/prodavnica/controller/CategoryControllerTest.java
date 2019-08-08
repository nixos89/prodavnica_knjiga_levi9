package com.levi9.prodavnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.CategoryConstants;
import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.dto.CategoryListDTO;
import com.levi9.prodavnica.service.CategoryService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findAllCategories() throws Exception{
        when(categoryService.findAllCategories()).thenReturn(new CategoryListDTO(Lists.newArrayList(
                new CategoryDTO(CategoryConstants.category0id,CategoryConstants.category0name,CategoryConstants.category0isDeleted),
                new CategoryDTO(CategoryConstants.category1id,CategoryConstants.category1name,CategoryConstants.category1isDeleted),
                new CategoryDTO(CategoryConstants.category2id,CategoryConstants.category2name,CategoryConstants.category2isDeleted))));
        mockMvc.perform(get(UrlPrefix.GET_CATEGORIES).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.categories.[0].name").value(CategoryConstants.category0name))
                .andExpect(jsonPath("$.categories.[0].categoryId").value(CategoryConstants.category0id))
                .andExpect(jsonPath("$.categories.[0].deleted").value(CategoryConstants.category0isDeleted))
                .andExpect(jsonPath("$.categories.[1].categoryId").value(CategoryConstants.category1id))
                .andExpect(jsonPath("$.categories.[1].name").value(CategoryConstants.category1name))
                .andExpect(jsonPath("$.categories.[1].deleted").value(CategoryConstants.category1isDeleted))
                .andDo(document("{class-name}/{method-name}",categoryFindAllCollection()));
    }

    @Test
    public void findOneCategory() throws Exception{
        when(categoryService.getOne(CategoryConstants.category0id)).thenReturn(CategoryConstants.create());
        mockMvc.perform(get(UrlPrefix.GET_CATEGORIES+"/"+CategoryConstants.category0id).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(CategoryConstants.category0name))
                .andExpect(jsonPath("$.categoryId").value(CategoryConstants.category0id))
                .andExpect(jsonPath("$.deleted").value(CategoryConstants.category0isDeleted))
                .andDo(document("{class-name}/{method-name}",categoryCollection()));
    }

    @Test
    public void addCategory() throws Exception{
        mockMvc.perform(post(UrlPrefix.GET_CATEGORIES).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(CategoryConstants.createAdd())))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCategory() throws Exception{
        mockMvc.perform(put(UrlPrefix.GET_CATEGORIES+"/"+ CategoryConstants.category0id).contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(CategoryConstants.createAdd())))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCategory() throws Exception{
        mockMvc.perform(delete(UrlPrefix.GET_CATEGORIES+"/"+ CategoryConstants.category0id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    private ResponseFieldsSnippet categoryFindAllCollection(){
        return responseFields(
                fieldWithPath("categories.[].categoryId").description("The unique identifier for gentre/category"),
                fieldWithPath("categories.[].name").description("Tha name of genre/category"),
                fieldWithPath("categories.[].deleted").description("Logical for genre/category")
        );
    }private ResponseFieldsSnippet categoryCollection(){
        return responseFields(
                fieldWithPath("categoryId").description("The unique identifier for gentre/category"),
                fieldWithPath("name").description("Tha name of genre/category"),
                fieldWithPath("deleted").description("Logical for genre/category")
        );
    }


}
