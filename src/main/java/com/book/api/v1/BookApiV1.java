package com.book.api.v1;

import com.book.api.v1.dto.BookDto;
import com.book.api.v1.dto.CreateBookDto;
import com.book.api.v1.dto.UpdateBookDto;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookRating;
import com.book.domain.BookStockCount;
import com.book.domain.BookSummary;
import com.book.domain.BookTitle;
import com.book.domain.exception.BookNotFoundException;
import com.book.usecase.CreateBooksUseCase;
import com.book.usecase.DeleteBookByIdUseCase;
import com.book.usecase.RetrieveBookByIdUseCase;
import com.book.usecase.RetrieveBooksUseCase;
import com.book.domain.Book;
import com.book.usecase.UpdateBookUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${spring.application.api-v1}")
public class BookApiV1 {

    private final RetrieveBooksUseCase retrieveBooksUseCase;
    private final CreateBooksUseCase createBookUseCase;
    private final RetrieveBookByIdUseCase retrieveBookByIdUseCase;
    private final DeleteBookByIdUseCase deleteBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;

    @Autowired
    public BookApiV1(RetrieveBooksUseCase retrieveBooksUseCase,
                     CreateBooksUseCase createBookUseCase,
                     RetrieveBookByIdUseCase retrieveBookByIdUseCase,
                     DeleteBookByIdUseCase deleteBookUseCase,
                     UpdateBookUseCase updateBookUseCase) {
        this.retrieveBooksUseCase = retrieveBooksUseCase;
        this.createBookUseCase = createBookUseCase;
        this.retrieveBookByIdUseCase = retrieveBookByIdUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
        this.updateBookUseCase = updateBookUseCase;
    }

    @GetMapping(path = "/books")
    public List<BookDto> getBooks(){
        return this.retrieveBooksUseCase.exe().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{id}")
    public BookDto getBookById(@PathVariable String id){
         return this.retrieveBookByIdUseCase.exe(BookId.of(id))
                 .map(this::toDto)
                 .orElseThrow(() -> new BookNotFoundException("The book was not found by id: " + id));
    }

    @PostMapping(path = "/books")
    public BookDto postBook(@Valid @RequestBody CreateBookDto createBookDto){
        var bookToCreate = toDomainEntity(createBookDto);
        var bookCreated = this.createBookUseCase.exe(bookToCreate);
        return toDto(bookCreated);
    }

    @DeleteMapping(path = "/books/{id}")
    public BookDto deleteBookById(@PathVariable String id){
        return this.deleteBookUseCase.exe(BookId.of(id))
                .map(this::toDto)
                .orElseThrow(() -> new BookNotFoundException("The book was not found by id: " + id));
    }

    @PutMapping(path = "/books/{id}")
    public BookDto putBook(@PathVariable String id, @Valid @RequestBody UpdateBookDto updateBookDto){
        var bookToUpdate = toDomainEntityUpdate(id, updateBookDto);
        return this.updateBookUseCase.exe(bookToUpdate)
                .map(this::toDto)
                .orElseThrow(() -> new BookNotFoundException("The book was not found by id: " + id));
    }

    private Book toDomainEntity(CreateBookDto dto) {
        return Book.builder()
                .title(BookTitle.of(dto.getTitle()))
                .author(BookAuthor.of(dto.getAuthor()))
                .summary(BookSummary.of(dto.getSummary()))
                .stockCount(BookStockCount.of(dto.getStockCount()))
                .rating(BookRating.of(dto.getRating()))
                .build();
    }

    private Book toDomainEntityUpdate(String rawBookId, UpdateBookDto dto) {
        return Book.builder()
                .id(BookId.of(rawBookId))
                .summary(BookSummary.of(dto.getSummary()))
                .stockCount(BookStockCount.of(dto.getStockCount()))
                .rating(BookRating.of(dto.getRating()))
                .build();
    }

    private BookDto toDto (Book book) {
        var bookDto = new BookDto(
                book.getId().getValue(),
                book.getTitle().getValue(),
                book.getAuthor().getValue(),
                book.getSummary().getValue(),
                book.getStockCount().getValue(),
                book.getRating().getValue()
        );
        return bookDto;
    }
}
