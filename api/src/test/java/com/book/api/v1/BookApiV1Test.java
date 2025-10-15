package com.book.api.v1;

import com.book.api.v1.dto.CreateBookDto;
import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookTitle;
import com.book.usecase.CreateBooksUseCase;
import com.book.usecase.RetrieveBookByIdUseCase;
import com.book.usecase.RetrieveBooksUseCase;
import com.book.usecase.UseCase;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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
        BDDMockito.given(this.retrieveBooksUseCase.exe()).willReturn(bookListMock);

        //when
        var response = this.bookApiV1.getBooks();

        //then
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(bookListMock.get(0).getId().getValue(), response.get(0).getId());
        Assertions.assertEquals(bookListMock.get(0).getTitle().getValue(), response.get(0).getTitle());
        Assertions.assertEquals(bookListMock.get(0).getAuthor().getValue(), response.get(0).getAuthor());
    }

    @Test
    public void shouldReturn100BooksTest() {
        //given
        var bookListMock = Instancio.ofList(Book.class).size(100).create();
        BDDMockito.given(this.retrieveBooksUseCase.exe()).willReturn(bookListMock);

        //when
        var response = this.bookApiV1.getBooks();

        //then
        Assertions.assertEquals(100, response.size());
    }

    @Test
    public void shouldCreate1BookTest() {
        //given
        var createBookDtoMock = Instancio.of(CreateBookDto.class).create();
        var bookId = BookId.of(UUID.randomUUID().toString());
        var bookMock = Instancio.of(Book.class)
                .set(Select.field(Book::getId), bookId)
                .set(Select.field(Book::getTitle), BookTitle.of(createBookDtoMock.getTitle()))
                .set(Select.field(Book::getAuthor), BookAuthor.of(createBookDtoMock.getAuthor()))
                .create();
        BDDMockito.given(this.createBooksUseCase.exe(any(CreateBookDto.class))).willReturn(bookMock);

        //when
        var response = this.bookApiV1.postBook(createBookDtoMock);

        //then
        Assertions.assertEquals(bookMock.getId().getValue(), response.getId());
        Assertions.assertEquals(bookMock.getTitle().getValue(), response.getTitle());
        Assertions.assertEquals(bookMock.getAuthor().getValue(), response.getAuthor());

    }

    @Test
    public void shouldReturnBookWithId1Test() {
        //given
        var bookId = BookId.of(UUID.randomUUID().toString());
        var bookFoundMock = Instancio.of(Book.class)
                .set(Select.field(Book::getId), bookId)
                .create();
        BDDMockito.given(this.retrieveBookByIdUseCase.exe(anyString()))
                .willReturn(Optional.ofNullable(bookFoundMock));

        //when
        var response = this.bookApiV1.getBookById(bookId.getValue());

        //then
        Assertions.assertEquals(bookId.getValue(), response.getId());
    }
}
