package com.book.domain.dao;

import com.book.domain.dao.record.BookRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookDao implements SimpleDao<String, BookRecord> {

    private final Map<String, BookRecord> recordMap;

    public BookDao(){
        recordMap = new HashMap<>();
    }

    @Override
    public List<BookRecord> findAll() {
        return this.recordMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookRecord> findById(String rawBookId) {
        return Optional.ofNullable(recordMap.get(rawBookId));
    }

    @Override
    public BookRecord createBook(BookRecord record) {
        var bookId = UUID.randomUUID().toString();
        var bookRecordWithId = record.toBuilder().id(bookId).build();
        recordMap.put(bookId, bookRecordWithId);
        return bookRecordWithId;
    }

    @Override
    public Optional<BookRecord> deleteBook(String rawBookId) {
        return Optional.ofNullable(this.recordMap.remove(rawBookId));
    }

    @Override
    public BookRecord updateBook(BookRecord record) {
        recordMap.put(record.getId(), record);
        return record;
    }
}
