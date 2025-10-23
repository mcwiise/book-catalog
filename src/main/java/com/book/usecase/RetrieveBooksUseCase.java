package com.book.usecase;

import com.book.domain.Book;
import com.book.domain.BookAuthor;
import com.book.domain.BookId;
import com.book.domain.BookRating;
import com.book.domain.BookStockCount;
import com.book.domain.BookSummary;
import com.book.domain.BookTitle;
import com.book.domain.FilterCriteria;
import com.book.domain.dao.SimpleDao;
import com.book.domain.dao.record.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Objects;

public class RetrieveBooksUseCase implements UseCase<Optional<FilterCriteria>, List<Book>> {

    private final SimpleDao<String, BookRecord> bookDao;

    @Autowired
    public RetrieveBooksUseCase(SimpleDao<String, BookRecord> bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> exe(Optional<FilterCriteria> filterCriteriaOptional) {
        return filterCriteriaOptional
                .map(filterCriteria ->
                    this.bookDao.findAll()
                            .stream()
                            .filter( bookRecord -> {
                               return this.filterInformation(filterCriteria, bookRecord);
                            }).map( this::toDomainEntity)
                            .collect(Collectors.toList())
                ).orElseGet(() -> this.bookDao.findAll()
                        .stream()
                        .map(this::toDomainEntity)
                        .collect(Collectors.toList()));
    }

    private boolean filterInformation(FilterCriteria criteria, BookRecord bookRecord) {
        // If criteria is null (shouldn't happen when called from exe), allow the record
        if (criteria == null) {
            return true;
        }

        // Title filter: if provided, record's title must equal the criteria
        if (criteria.getTitle() != null && criteria.getTitle().getValue() != null) {
            if (!Objects.equals(bookRecord.getTitle(), criteria.getTitle().getValue())) {
                return false;
            }
        }

        // Rating filter: if provided, record's rating must equal the criteria
        if (criteria.getRating() != null && criteria.getRating().getValue() != null) {
            if (!Objects.equals(bookRecord.getRating(), criteria.getRating().getValue())) {
                return false;
            }
        }

        // Author filter: if provided, record's author must equal the criteria
        if (criteria.getAuthor() != null && criteria.getAuthor().getValue() != null) {
            if (!Objects.equals(bookRecord.getAuthor(), criteria.getAuthor().getValue())) {
                return false;
            }
        }

        // If none of the provided criteria excluded the record, it's a match
        return true;
    }

    private Book toDomainEntity(BookRecord record) {
        return Book.builder()
                .id(BookId.of(record.getId()))
                .title(BookTitle.of(record.getTitle()))
                .author(BookAuthor.of(record.getAuthor()))
                .summary(BookSummary.of(record.getSummary()))
                .stockCount(BookStockCount.of(record.getStockCount()))
                .rating(BookRating.of(record.getRating()))
                .build();
    }


}
