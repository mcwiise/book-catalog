package com.book.usecase;

import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookTitle;
import com.book.domain.dao.SimpleDao;
import com.book.domain.dao.record.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RetrieveBooksUseCase implements SimpleUseCase<List<Book>> {

    private final SimpleDao<String, BookRecord> bookDao;

    @Autowired
    public RetrieveBooksUseCase(SimpleDao<String, BookRecord> bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> exe() {
        return bookDao.findAll().stream()
                .map(this::toDomainEntity)
                .collect(Collectors.toList());
    }

    public Book toDomainEntity(BookRecord bookRecord) {
        return Book.builder().id(BookId.of(bookRecord.getId()))
                .title(BookTitle.of(bookRecord.getTitle()))
                .author(BookAuthor.of(bookRecord.getAuthor()))
                .build();
    }
}
