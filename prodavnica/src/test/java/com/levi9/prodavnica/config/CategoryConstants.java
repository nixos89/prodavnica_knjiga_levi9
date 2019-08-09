package com.levi9.prodavnica.config;

import com.levi9.prodavnica.dto.AddCategoryDTO;
import com.levi9.prodavnica.dto.CategoryDTO;

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

    public static final CategoryDTO create(){
        return new CategoryDTO(category0id,category0name,category0isDeleted);
    }
    public static final AddCategoryDTO createAdd(){
        return new AddCategoryDTO(category0name,false);
    }

}
