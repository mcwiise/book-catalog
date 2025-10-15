package com.book;

import com.book.api.v1.dto.CreateBookDto;
import com.book.usecase.UseCase;
import com.book.usecase.Book;
import com.book.usecase.CreateBooksUseCase;
import com.book.usecase.RetrieveBooksUseCase;
import com.book.usecase.SimpleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiConfig {

    @Bean
    public SimpleUseCase<List<Book>> getRetrieveBooksUseCaseBean() {
        return new RetrieveBooksUseCase();
    }

    @Bean
    public UseCase<CreateBookDto, Book> getCreateBookUseCaseBean() {
        return new CreateBooksUseCase();
    }
}
