package com.levi9.prodavnica.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookListDTO {

    List<BookDTO> bookDTOS;

    public BookListDTO(){
        this.bookDTOS = new ArrayList<>();
    }

}
