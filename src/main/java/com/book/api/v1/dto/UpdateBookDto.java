package com.book.api.v1.dto;

import lombok.Value;

@Value
public class UpdateBookDto {
    String summary;
    Integer stockCount;
    Integer rating;
}
