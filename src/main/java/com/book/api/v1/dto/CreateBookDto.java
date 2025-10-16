package com.book.api.v1.dto;

import lombok.Value;

@Value
public class CreateBookDto {
    String title;
    String author;
}
