package com.book.usecase;

import com.book.domain.dao.BookDao;
import com.book.domain.dao.record.BookRecord;
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

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class RetrieveBookByIdUseCaseTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private RetrieveBookByIdUseCase retrieveBookByIdUseCase;

    @Test
    public void shouldRetrieveAllBooksTest(){
        //given
        var bookIdMock = "1";
        var bookRecordMock = Instancio.of(BookRecord.class)
                .set(Select.field(BookRecord::getId), bookIdMock)
                .create();
        BDDMockito.given(this.bookDao.findById(anyString())).willReturn(Optional.of(bookRecordMock));

        //when
        var response = this.retrieveBookByIdUseCase.exe(bookIdMock);

        Assertions.assertTrue(response.isPresent());
        Assertions.assertEquals(bookRecordMock.getId(), response.get().getId().getValue());
        Assertions.assertEquals(bookRecordMock.getTitle(), response.get().getTitle().getValue());
        Assertions.assertEquals(bookRecordMock.getAuthor(), response.get().getAuthor().getValue());
    }
}
