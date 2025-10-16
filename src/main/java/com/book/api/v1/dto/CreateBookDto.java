package com.book.api.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreateBookDto {
    @NotBlank
    String title;
    @NotBlank
    String author;
    @NotBlank
    String summary;
    @NotNull
    Integer stockCount;
    @NotNull
    Integer rating;
}
