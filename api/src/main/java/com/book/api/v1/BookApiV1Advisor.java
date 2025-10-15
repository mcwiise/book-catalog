package com.book.api.v1;

import com.book.api.v1.dto.BookDto;
import com.book.api.v1.dto.CreateBookDto;
import com.book.api.v1.dto.HttpErrorBody;
import com.book.domain.Book;
import com.book.domain.exception.BookNotFoundException;
import com.book.usecase.SimpleUseCase;
import com.book.usecase.UseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class BookApiV1Advisor {
    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<HttpErrorBody> handleBookNotFoundException(BookNotFoundException ex) {
        var errBody = new HttpErrorBody(LocalDateTime.now(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errBody);
    }
}
