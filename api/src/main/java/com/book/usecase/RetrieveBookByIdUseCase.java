package com.book.usecase;

import com.book.domain.Book;

import java.util.Optional;

public class RetrieveBookByIdUseCase implements UseCase<String, Optional<Book>> {
    @Override
    public Optional<Book> exe(String rawBookId) {
        return Optional.empty();
    }
}
