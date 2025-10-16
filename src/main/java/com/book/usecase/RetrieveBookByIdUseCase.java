package com.book.usecase;

import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookTitle;
import com.book.domain.dao.SimpleDao;
import com.book.domain.dao.record.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RetrieveBookByIdUseCase implements UseCase<String, Optional<Book>> {

    private final SimpleDao<String, BookRecord> bookDao;

    @Autowired
    public RetrieveBookByIdUseCase(SimpleDao<String, BookRecord> bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public Optional<Book> exe(String rawBookId) {
        return this.bookDao.findById(rawBookId)
                .map(this::toDomainEntity);
    }

    public Book toDomainEntity(BookRecord bookRecord) {
        return Book.builder().id(BookId.of(bookRecord.getId()))
                .title(BookTitle.of(bookRecord.getTitle()))
                .author(BookAuthor.of(bookRecord.getAuthor()))
                .build();
    }
}
