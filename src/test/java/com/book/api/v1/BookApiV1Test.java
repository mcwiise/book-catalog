package com.book.api.v1;

import com.book.api.v1.dto.CreateBookDto;
import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookRating;
import com.book.domain.BookStockCount;
import com.book.domain.BookSummary;
import com.book.domain.BookTitle;
import com.book.domain.exception.BookNotFoundException;
import com.book.usecase.CreateBooksUseCase;
import com.book.usecase.RetrieveBookByIdUseCase;
import com.book.usecase.RetrieveBooksUseCase;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BookApiV1Test {

    @Mock
    private RetrieveBooksUseCase retrieveBooksUseCase;

    @Mock
    private CreateBooksUseCase createBooksUseCase;

    @Mock
    private RetrieveBookByIdUseCase retrieveBookByIdUseCase;

    @InjectMocks
    private BookApiV1 bookApiV1;

    @Test
    public void shouldReturn1BookTest() {
        //given
        var bookListMock = Instancio.ofList(Book.class).size(1).create();
        BDDMockito.given(this.retrieveBooksUseCase.exe(org.mockito.ArgumentMatchers.<Optional<com.book.domain.FilterCriteria>>any())).willReturn(bookListMock);

        //when
        var response = this.bookApiV1.getBooks(null, null, null);

        //then
        Assertions.assertEquals(bookListMock.get(0).getId().getValue(), response.get(0).getId());
        Assertions.assertEquals(bookListMock.get(0).getTitle().getValue(), response.get(0).getTitle());
        Assertions.assertEquals(bookListMock.get(0).getAuthor().getValue(), response.get(0).getAuthor());
        Assertions.assertEquals(bookListMock.get(0).getSummary().getValue(), response.get(0).getSummary());
        Assertions.assertEquals(bookListMock.get(0).getStockCount().getValue(), response.get(0).getStockCount());
        Assertions.assertEquals(bookListMock.get(0).getRating().getValue(), response.get(0).getRating());
    }

    @Test
    public void shouldReturn100BooksTest() {
        //given
        var bookListMock = Instancio.ofList(Book.class).size(100).create();
        BDDMockito.given(this.retrieveBooksUseCase.exe(org.mockito.ArgumentMatchers.<Optional<com.book.domain.FilterCriteria>>any())).willReturn(bookListMock);

        //when
        var response = this.bookApiV1.getBooks(null, null, null);

        //then
        Assertions.assertEquals(100, response.size());
    }

    @Test
    public void shouldThrowBookNotFoundExceptionWhenRetrieveABookByIdTest() {
        // given
        BDDMockito.given(this.retrieveBookByIdUseCase.exe(any(BookId.class)))
                .willReturn(Optional.empty());

        // when & then
        var ex = Assertions.assertThrows(BookNotFoundException.class, () -> {
            this.bookApiV1.getBookById("1");
        });
        Assertions.assertTrue(ex.getMessage().contains("1"));
        BDDMockito.then(this.retrieveBookByIdUseCase).should().exe(BookId.of("1"));
    }

    @Test
    public void shouldRetrieveABookByIdTest() {
        // given
        var bookMock = Instancio.of(Book.class).create();
        BDDMockito.given(this.retrieveBookByIdUseCase.exe(any(BookId.class)))
                .willReturn(Optional.of(bookMock));

        // when
        var response = this.bookApiV1.getBookById(bookMock.getId().getValue());

        //then
        Assertions.assertEquals(bookMock.getId().getValue(), response.getId());
        Assertions.assertEquals(bookMock.getTitle().getValue(), response.getTitle());
        Assertions.assertEquals(bookMock.getAuthor().getValue(), response.getAuthor());
        Assertions.assertEquals(bookMock.getSummary().getValue(), response.getSummary());
        Assertions.assertEquals(bookMock.getStockCount().getValue(), response.getStockCount());
        Assertions.assertEquals(bookMock.getRating().getValue(), response.getRating());
    }

    @Test
    public void shouldCreateABookTest() {
        // given
        var createBookDto = Instancio.of(CreateBookDto.class).create();
        var bookIdMock = BookId.of(java.util.UUID.randomUUID().toString());
        var bookMock = Instancio.of(Book.class)
                .set(Select.field(Book::getId), bookIdMock)
                .set(Select.field(Book::getTitle), BookTitle.of(createBookDto.getTitle()))
                .set(Select.field(Book::getAuthor), BookAuthor.of(createBookDto.getAuthor()))
                .set(Select.field(Book::getSummary), BookSummary.of(createBookDto.getSummary()))
                .set(Select.field(Book::getStockCount), BookStockCount.of(createBookDto.getStockCount()))
                .set(Select.field(Book::getRating), BookRating.of(createBookDto.getRating()))
                .create();
        BDDMockito.given(this.createBooksUseCase.exe(any(Book.class)))
                .willReturn(bookMock);

        // when
        var response = this.bookApiV1.postBook(createBookDto);

        //then
        Assertions.assertEquals(bookMock.getId().getValue(), response.getId());
        Assertions.assertEquals(bookMock.getTitle().getValue(), response.getTitle());
        Assertions.assertEquals(bookMock.getAuthor().getValue(), response.getAuthor());
        Assertions.assertEquals(bookMock.getSummary().getValue(), response.getSummary());
        Assertions.assertEquals(bookMock.getStockCount().getValue(), response.getStockCount());
        Assertions.assertEquals(bookMock.getRating().getValue(), response.getRating());
    }
}
