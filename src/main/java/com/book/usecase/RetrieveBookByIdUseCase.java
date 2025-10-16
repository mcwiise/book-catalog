package com.book.usecase;

import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookRating;
import com.book.domain.BookStockCount;
import com.book.domain.BookSummary;
import com.book.domain.BookTitle;
import com.book.domain.dao.SimpleDao;
import com.book.domain.dao.record.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RetrieveBookByIdUseCase implements UseCase<BookId, Optional<Book>> {

    private final SimpleDao<String, BookRecord> bookDao;

    @Autowired
    public RetrieveBookByIdUseCase(SimpleDao<String, BookRecord> bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public Optional<Book> exe(BookId bookId) {
        return this.bookDao.findById(bookId.getValue())
                .map(this::toDomainEntity);
    }

    private Book toDomainEntity(BookRecord record) {
        return Book.builder()
                .id(BookId.of(record.getId()))
                .title(BookTitle.of(record.getTitle()))
                .author(BookAuthor.of(record.getAuthor()))
                .summary(BookSummary.of(record.getSummary()))
                .stockCount(BookStockCount.of(record.getStockCount()))
                .rating(BookRating.of(record.getRating()))
                .build();
    }
}
