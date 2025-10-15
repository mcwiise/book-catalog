package com.book.usecase;

import lombok.Value;

@Value(staticConstructor = "of")
public class BookTitle {
    String value;
}
