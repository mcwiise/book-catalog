package com.book.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class BookId {
    String value;
}
