package com.book.api.v1;

import com.book.api.v1.vo.BookVO;
import com.book.usecase.RetrieveBooksUseCase;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookApiV1Test {

    @Mock
    private RetrieveBooksUseCase retrieveBooksUseCase;

    @InjectMocks
    private BookApiV1 bookApiV1;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(retrieveBooksUseCase);
    }

    @Test
    public void shouldReturn10Books() {
        //given
        var bookListMock = Instancio.ofList(BookVO.class).size(10).create();
        BDDMockito.given(this.retrieveBooksUseCase.exe()).willReturn(bookListMock);

        //when
        var response = this.bookApiV1.getBooks();

        //then
        Assertions.assertEquals(10, response.size());
    }
}
