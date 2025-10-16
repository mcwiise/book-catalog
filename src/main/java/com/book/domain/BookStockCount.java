package com.book.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class BookStockCount {
    Integer value;
}
