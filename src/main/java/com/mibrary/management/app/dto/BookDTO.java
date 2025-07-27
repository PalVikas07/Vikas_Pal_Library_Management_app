package com.mibrary.management.app.dto;

import lombok.Data;

@Data
public class BookDTO {

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private Integer quantity;
    private boolean isAvailable;
}
