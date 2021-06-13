package com.bookapp.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    private String title;
    private String author;
    private String category;
    private int bookId;
}
