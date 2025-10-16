package com.book.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class BookRating {
    Integer value;
}
