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
public class CreateBookUseCaseTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private CreateBooksUseCase createBooksUseCase;


}
