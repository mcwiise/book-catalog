package com.book.domain;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class FilterCriteria {
    private BookTitle title;
    private BookAuthor author;
    private BookRating rating;
}
