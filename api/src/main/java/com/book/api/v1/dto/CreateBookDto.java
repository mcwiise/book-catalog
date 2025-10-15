package com.book.api.v1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookDto {
    @NotNull
    private String title;
    @NotNull
    private String author;
}
