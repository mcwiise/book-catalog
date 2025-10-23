package com.book.domain.dao;

import java.util.List;
import java.util.Optional;

public interface SimpleDao<ID,R> {
    List<R> findAll();
    Optional<R> findById(ID id);
    R createBook(R record);
    Optional<R> deleteBook(ID id);
    R updateBook(R record);
}
