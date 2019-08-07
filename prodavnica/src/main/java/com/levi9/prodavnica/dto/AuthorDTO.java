package com.levi9.prodavnica.dto;

import com.levi9.prodavnica.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Long authorId;
    private String firstName;
    private String lastName;

}
