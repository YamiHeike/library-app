package com.library.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Book {
    long id;
    String title;
    String author;
}
