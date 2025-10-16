package com.book.usecase;

import com.book.api.v1.dto.CreateBookDto;
import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookTitle;
import com.book.domain.dao.BookDao;
import com.book.domain.dao.record.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateBooksUseCase implements UseCase<Book, Book> {

    private final BookDao bookDao;

    @Autowired
    public  CreateBooksUseCase(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book exe(Book bookToCreate) {
        var bookRecordToCreate = toRecord(bookToCreate);
        var bookRecordCreated = this.bookDao.createBook(bookRecordToCreate);
        return toDomainEntity(bookRecordCreated);
    }

    private BookRecord toRecord(Book book){
        return BookRecord.builder()
                .title(book.getTitle().getValue())
                .author(book.getAuthor().getValue())
                .build();
    }

    private Book toDomainEntity(BookRecord bookRecord){
        return Book.builder()
                .id(BookId.of(bookRecord.getId()))
                .title(BookTitle.of(bookRecord.getTitle()))
                .author(BookAuthor.of(bookRecord.getAuthor()))
                .build();
    }
}
