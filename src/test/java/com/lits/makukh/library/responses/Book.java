package com.lits.makukh.library.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private String publishDate;
    private String description;
    private String author;
    private String isbn;
    private String publisher;
    private String name;
}
