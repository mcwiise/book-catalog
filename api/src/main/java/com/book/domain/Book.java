package com.book.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Book {
    private BookId id;
    private BookTitle title;
    private BookAuthor author;
}
