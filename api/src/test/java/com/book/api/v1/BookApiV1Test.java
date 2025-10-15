package com.book.api.v1;

import com.book.api.v1.dto.BookDto;
import com.book.api.v1.dto.CreateBookDto;
import com.book.usecase.Book;
import com.book.usecase.BookAuthor;
import com.book.usecase.BookId;
import com.book.usecase.BookTitle;
import com.book.usecase.CreateBooksUseCase;
import com.book.usecase.RetrieveBooksUseCase;
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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BookApiV1Test {

    @Mock
    private RetrieveBooksUseCase retrieveBooksUseCase;

    @Mock
    private CreateBooksUseCase createBooksUseCase;

    @InjectMocks
    private BookApiV1 bookApiV1;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(retrieveBooksUseCase);
    }

    @Test
    public void shouldReturn1Books() {
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
    public void shouldReturn100Books() {
        //given
        var bookListMock = Instancio.ofList(Book.class).size(100).create();
        BDDMockito.given(this.retrieveBooksUseCase.exe()).willReturn(bookListMock);

        //when
        var response = this.bookApiV1.getBooks();

        //then
        Assertions.assertEquals(100, response.size());
    }

    @Test
    public void shouldCreate1Book() {
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
}
