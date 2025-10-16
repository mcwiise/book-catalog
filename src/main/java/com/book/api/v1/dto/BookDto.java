package com.book.api.v1.dto;

import lombok.Value;
import lombok.With;

@Value
@With
public class BookDto {
    String id;
    String title;
    String author;
}
