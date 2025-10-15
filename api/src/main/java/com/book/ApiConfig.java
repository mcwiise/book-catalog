package com.book;

import com.book.api.v1.dto.CreateBookDto;
import com.book.usecase.RetrieveBookByIdUseCase;
import com.book.usecase.UseCase;
import com.book.domain.Book;
import com.book.usecase.CreateBooksUseCase;
import com.book.usecase.RetrieveBooksUseCase;
import com.book.usecase.SimpleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

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

    @Bean
    public UseCase<String, Optional<Book>> getRetrieveBookByIdUseCaseBean() {
        return new RetrieveBookByIdUseCase();
    }

}
