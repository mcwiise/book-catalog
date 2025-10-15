package com.book.usecase;

import java.util.Collections;
import java.util.List;

public class RetrieveBooksUseCase implements SimpleUseCase<List<Book>> {
    @Override
    public List<Book> exe() {
        return Collections.emptyList();
    }
}
