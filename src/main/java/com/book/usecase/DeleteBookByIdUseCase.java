package com.book.usecase;

import com.book.domain.BookId;

import java.util.Optional;

public class DeleteBookByIdUseCase implements UseCase<String, Optional<BookId>> {
    @Override
    public Optional<BookId> exe(String rawBookId) {
        return Optional.empty();
    }
}
