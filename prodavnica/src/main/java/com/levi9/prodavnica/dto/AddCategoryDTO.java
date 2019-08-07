package com.levi9.prodavnica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryDTO {

    private String name;
    private Boolean isDeleted;

}
