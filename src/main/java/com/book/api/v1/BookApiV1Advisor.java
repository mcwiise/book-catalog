package com.book.api.v1;

import com.book.api.v1.dto.HttpErrorBody;
import com.book.domain.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class BookApiV1Advisor {

    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<HttpErrorBody> handleBookNotFoundException(BookNotFoundException ex) {
        var errBody = new HttpErrorBody(LocalDateTime.now(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errBody);
    }
}
