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
    public void shouldRetrieveEmptyOptionalWhenNoBookFoundTest(){
        //given
        BDDMockito.given(this.bookDao.findById(anyString())).willReturn(Optional.empty());

        //when
        var response = this.retrieveBookByIdUseCase.exe("1");

        Assertions.assertFalse(response.isPresent());
    }

    @Test
    public void shouldRetrieveABookByIdTest(){
        //given
        var rawBookId = "1";
        var bookRecord = Instancio.of(BookRecord.class)
                .set(Select.field(BookRecord::getId), rawBookId)
                .create();
        BDDMockito.given(this.bookDao.findById(anyString())).willReturn(Optional.of(bookRecord));

        //when
        var response = this.retrieveBookByIdUseCase.exe(rawBookId);

        //then
        Assertions.assertTrue(response.isPresent());
        Assertions.assertEquals(rawBookId, response.get().getId().getValue());
        Assertions.assertEquals(bookRecord.getTitle(), response.get().getTitle().getValue());
        Assertions.assertEquals(bookRecord.getAuthor(), response.get().getAuthor().getValue());
    }
}
