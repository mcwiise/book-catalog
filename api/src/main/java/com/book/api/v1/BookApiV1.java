package com.book.api.v1;

import com.book.api.v1.dto.BookDto;
import com.book.api.v1.dto.CreateBookDto;
import com.book.usecase.UseCase;
import com.book.usecase.BookAuthor;
import com.book.usecase.Book;
import com.book.usecase.BookTitle;
import com.book.usecase.SimpleUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${spring.application.api-v1}")
public class BookApiV1 {

    private final SimpleUseCase<List<Book>> retrieveBooksUseCase;
    private final UseCase<CreateBookDto, Book> createBookUseCase;


    @Autowired
    public BookApiV1(SimpleUseCase<List<Book>> retrieveBooksUseCase,
                     UseCase<CreateBookDto, Book> createBookUseCase) {
        this.retrieveBooksUseCase = retrieveBooksUseCase;
        this.createBookUseCase = createBookUseCase;
    }

    @GetMapping(path = "/books")
    public List<BookDto> getBooks(){
        return this.retrieveBooksUseCase.exe().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/books")
    public BookDto postBook(@Valid @RequestBody CreateBookDto createBookDto){
        var bookCreated = this.createBookUseCase.exe(createBookDto);
        return toDto(bookCreated);
    }

    private BookDto toDto (Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId().getValue());
        bookDto.setAuthor(book.getAuthor().getValue());
        bookDto.setTitle(book.getTitle().getValue());
        return bookDto;
    }
}
