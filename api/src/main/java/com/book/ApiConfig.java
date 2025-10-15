package com.book;

import com.book.api.v1.vo.BookVO;
import com.book.usecase.RetrieveBooksUseCase;
import com.book.usecase.SimpleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiConfig {

    @Bean
    public SimpleUseCase<List<BookVO>> getRetrieveBooksUseCase() {
        return new RetrieveBooksUseCase();
    }
}
