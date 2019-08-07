package com.levi9.prodavnica.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AuthorListDTO {

    private List<AuthorDTO> authors;

    public AuthorListDTO (){
        this.authors = new ArrayList<>();
    }

}
