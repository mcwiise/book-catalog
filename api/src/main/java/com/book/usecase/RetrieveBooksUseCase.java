package com.book.usecase;

import com.book.api.v1.vo.BookVO;

import java.util.ArrayList;
import java.util.List;

public class RetrieveBooksUseCase implements SimpleUseCase<List<BookVO>>{
    @Override
    public List<BookVO> exe() {
        List<BookVO> books = new ArrayList<>();
        BookVO book = new BookVO();
        book.setId("1L");
        book.setTitle("Book 1");
        book.setAuthor("Author 1");
        books.add(book);
        book = new BookVO();
        book.setId("2L");
        book.setTitle("Book 2");
        book.setAuthor("Author 2");
        books.add(book);
        return books;
    }
}
