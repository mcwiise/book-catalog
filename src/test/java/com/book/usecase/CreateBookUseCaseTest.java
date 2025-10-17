package com.book.usecase;

import com.book.domain.dao.BookDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class CreateBookUseCaseTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private CreateBooksUseCase createBooksUseCase;


}
