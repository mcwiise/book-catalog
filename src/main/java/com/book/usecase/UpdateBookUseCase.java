package com.book.usecase;

import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookRating;
import com.book.domain.BookStockCount;
import com.book.domain.BookSummary;
import com.book.domain.BookTitle;
import com.book.domain.dao.BookDao;
import com.book.domain.dao.record.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UpdateBookUseCase implements UseCase<Book, Optional<Book>> {

    private final BookDao bookDao;

    @Autowired
    public UpdateBookUseCase(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Optional<Book> exe(Book bookToUpdate) {
        return this.bookDao.findById(bookToUpdate.getId().getValue())
                .map(this::toDomainEntity)
                .map(bookFound -> this.mergeBooks(bookFound, bookToUpdate))
                .map(mergedBook -> {
                    var bookRecordToUpdate = toRecord(mergedBook);
                    var bookRecordUpdated = this.bookDao.updateBook(bookRecordToUpdate);
                    var bookUpdated = toDomainEntity(bookRecordUpdated);
                    return bookUpdated;
                });
    }

    private Book mergeBooks (Book bookFound, Book bookToUpdate) {
        return Book.builder()
                .id(bookFound.getId())
                .title(bookFound.getTitle())
                .author(bookFound.getAuthor())
                .summary(BookSummary.of(this.checkField(bookToUpdate.getSummary().getValue(), bookFound.getSummary().getValue())))
                .stockCount(BookStockCount.of(this.checkField(bookToUpdate.getStockCount().getValue(), bookFound.getStockCount().getValue())))
                .rating(BookRating.of(this.checkField(bookToUpdate.getRating().getValue(), bookFound.getRating().getValue())))
                .build();
    }

    private <T> T checkField(T newValue, T original) {
        if (newValue == null) {
            return original;
        } else if (newValue.equals(original)) {
            return original;
        } else {
            return newValue;
        }
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

    private BookRecord toRecord(Book book){
        return BookRecord.builder()
                .id(book.getId().getValue())
                .title(book.getTitle().getValue())
                .author(book.getAuthor().getValue())
                .summary(book.getSummary().getValue())
                .stockCount(book.getStockCount().getValue())
                .rating(book.getRating().getValue())
                .build();
    }
}
