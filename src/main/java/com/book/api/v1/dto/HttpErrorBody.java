package com.book.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class HttpErrorBody {
    private LocalDateTime timestamp;
    private String message;
}
