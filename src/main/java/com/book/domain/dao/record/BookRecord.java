package com.book.domain.dao.record;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@ToString
@Getter
public class BookRecord {
    private final String id;
    private final String title;
    private final String author;
}
