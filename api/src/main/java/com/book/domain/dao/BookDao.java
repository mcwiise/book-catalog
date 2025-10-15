package com.book.domain.dao;

import com.book.domain.dao.record.BookRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public Optional<BookRecord> findById(String s) {
        return Optional.empty();
    }
}
