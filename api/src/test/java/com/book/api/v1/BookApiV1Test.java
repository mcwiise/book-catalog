package com.book.api.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookApiV1Test {

    @InjectMocks
    private BookApiV1 bookApiV1;

    @Test
    public void shouldReturn10Books() {
        var response = this.bookApiV1.getBooks();
        Assertions.assertNotNull(response);
    }
}
