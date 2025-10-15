package com.book.usecase;

import com.book.api.v1.dto.CreateBookDto;

import java.util.UUID;

public class CreateBooksUseCase implements UseCase<CreateBookDto, Book> {
    @Override
    public Book exe(CreateBookDto bookDto) {
        var bookId = BookId.of(UUID.randomUUID().toString());
        var bookTitle = BookTitle.of(bookDto.getTitle());
        var bookAuthor = BookAuthor.of(bookDto.getAuthor());
        var bookToCreate = Book.builder().id(bookId).title(bookTitle).author(bookAuthor).build();
        return bookToCreate;
    }
}
