package com.book.domain.dao.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookRecord {
    private String id;
    private String title;
    private String author;
}
