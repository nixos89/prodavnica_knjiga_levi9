package com.levi9.prodavnica.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryListDTO {
    List<CategoryDTO> categories;

    public CategoryListDTO(){
        this.categories = new ArrayList<>();
    }
}
