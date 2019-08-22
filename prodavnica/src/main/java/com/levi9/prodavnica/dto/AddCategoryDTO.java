package com.levi9.prodavnica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryDTO {

    @NotEmpty(message = "Category name is required")
    private String name;
    private Boolean isDeleted;

}
