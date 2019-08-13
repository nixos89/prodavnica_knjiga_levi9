package com.levi9.prodavnica.config;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.dto.CategoryDTO;
import com.levi9.prodavnica.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryConstants {

    public static final Long category0id = 1L;
    public static final String category0name = "Komedija";
    public static final boolean category0isDeleted = false;

    public static final Long category1id = 2L;
    public static final String category1name = "Drama";
    public static final boolean category1isDeleted = false;

    public static final Long category2id = 3L;
    public static final String category2name = "Akcija";
    public static final boolean category2isDeleted = true;

    public static final Category createCategory(){return new Category(category0id,category0name,category0isDeleted);}

    public static final CategoryDTO create(){
        return new CategoryDTO(category0id,category0name,category0isDeleted);
    }
    public static final AddCategoryDTO createAdd(){
        return new AddCategoryDTO(category0name,false);
    }

    public static final List<Category> createListCategory(){
        List<Category> categories = new ArrayList<>();
             Category cat1 = new Category(CategoryConstants.category0id,CategoryConstants.category0name,CategoryConstants.category0isDeleted);
             Category cat2 = new Category(CategoryConstants.category1id,CategoryConstants.category1name,CategoryConstants.category1isDeleted);
             Category cat3 =  new Category(CategoryConstants.category2id,CategoryConstants.category2name,CategoryConstants.category2isDeleted);

             categories.add(cat1);
             categories.add(cat2);
             categories.add(cat3);
             return categories;
    }

}
