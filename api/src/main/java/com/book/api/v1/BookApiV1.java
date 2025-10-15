package com.book.api.v1;

import com.book.api.v1.dto.BookDto;
import com.book.api.v1.dto.CreateBookDto;
import com.book.domain.BookId;
import com.book.domain.exception.BookNotFoundException;
import com.book.usecase.UseCase;
import com.book.domain.Book;
import com.book.usecase.SimpleUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${spring.application.api-v1}")
public class BookApiV1 {

    private final SimpleUseCase<List<Book>> retrieveBooksUseCase;
    private final UseCase<CreateBookDto, Book> createBookUseCase;
    private final UseCase<String, Optional<Book>> retrieveBookByIdUseCase;
    private final UseCase<String, Optional<BookId>> deleteBookUseCase;


    @Autowired
    public BookApiV1(SimpleUseCase<List<Book>> retrieveBooksUseCase,
                     UseCase<CreateBookDto, Book> createBookUseCase,
                     UseCase<String, Optional<Book>> retrieveBookByIdUseCase,
                     UseCase<String, Optional<BookId>> deleteBookUseCase) {
        this.retrieveBooksUseCase = retrieveBooksUseCase;
        this.createBookUseCase = createBookUseCase;
        this.retrieveBookByIdUseCase = retrieveBookByIdUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
    }

    @GetMapping(path = "/books")
    public List<BookDto> getBooks(){
        return this.retrieveBooksUseCase.exe().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{id}")
    public BookDto getBookById(@PathVariable String id){
         return this.retrieveBookByIdUseCase.exe(id)
                 .map(this::toDto)
                 .orElseThrow(() -> new BookNotFoundException("The book was not found by id: " + id));
    }

    @PostMapping(path = "/books")
    public BookDto postBook(@Valid @RequestBody CreateBookDto createBookDto){
        var bookCreated = this.createBookUseCase.exe(createBookDto);
        return toDto(bookCreated);
    }

    @DeleteMapping(path = "/books/{id}")
    public BookId deleteBookById(@PathVariable String id){
        return this.deleteBookUseCase.exe(id)
                .orElseThrow(() -> new BookNotFoundException("The book was not found by id: " + id));
    }

    private BookDto toDto (Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId().getValue());
        bookDto.setAuthor(book.getAuthor().getValue());
        bookDto.setTitle(book.getTitle().getValue());
        return bookDto;
    }
}
