package com.book.api.v1.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class HttpErrorBody {
    LocalDateTime timestamp;
    String message;
}
