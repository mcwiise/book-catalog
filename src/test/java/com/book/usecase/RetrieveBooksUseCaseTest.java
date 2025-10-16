package com.book.usecase;

import com.book.domain.dao.BookDao;
import com.book.domain.dao.record.BookRecord;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RetrieveBooksUseCaseTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private RetrieveBooksUseCase retrieveBooksUseCase;

    @Test
    public void shouldRetrieveAllBooksTest(){
        //given
        var bookRecordListMock = Instancio.ofList(BookRecord.class).size(2).create();
        BDDMockito.given(this.bookDao.findAll()).willReturn(bookRecordListMock);

        //when
        var response = this.retrieveBooksUseCase.exe();

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals(bookRecordListMock.get(0).getId(), response.get(0).getId().getValue());
        Assertions.assertEquals(bookRecordListMock.get(0).getAuthor(), response.get(0).getAuthor().getValue());
        Assertions.assertEquals(bookRecordListMock.get(0).getTitle(), response.get(0).getTitle().getValue());
    }
}
