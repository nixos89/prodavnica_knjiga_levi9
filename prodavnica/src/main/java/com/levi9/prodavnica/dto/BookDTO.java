package com.levi9.prodavnica.dto;

import com.levi9.prodavnica.mapper.AuthorMapper;
import com.levi9.prodavnica.mapper.CategoryMapper;
import com.levi9.prodavnica.model.Author;
import com.levi9.prodavnica.model.Book;
import com.levi9.prodavnica.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long bookId;
    private String name;
    private double price;
    private int amount;
    private Set<AuthorDTO> authors = new HashSet<>();
    private Set<CategoryDTO> categories = new HashSet<>();

    }

