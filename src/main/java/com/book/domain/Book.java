package com.book.domain;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class Book {
    private BookId id;
    private BookTitle title;
    private BookAuthor author;
    private BookSummary summary;
    private BookStockCount stockCount;
    private BookRating rating;
}
