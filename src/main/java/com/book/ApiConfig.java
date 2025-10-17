package com.book;

import com.book.domain.dao.BookDao;
import com.book.usecase.DeleteBookByIdUseCase;
import com.book.usecase.RetrieveBookByIdUseCase;
import com.book.usecase.CreateBooksUseCase;
import com.book.usecase.RetrieveBooksUseCase;
import com.book.usecase.UpdateBookUseCase;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class ApiConfig {

    @Bean
    public RetrieveBooksUseCase getRetrieveBooksUseCaseBean(BookDao bookDao) {
        return new RetrieveBooksUseCase(bookDao);
    }

    @Bean
    public CreateBooksUseCase getCreateBookUseCaseBean(BookDao bookDao) {
        return new CreateBooksUseCase(bookDao);
    }

    @Bean
    public RetrieveBookByIdUseCase getRetrieveBookByIdUseCaseBean(BookDao bookDao) {
        return new RetrieveBookByIdUseCase(bookDao);
    }

    @Bean
    public DeleteBookByIdUseCase getDeleteBookByIdUseCaseBean(BookDao bookDao) {
        return new DeleteBookByIdUseCase(bookDao);
    }

    @Bean
    public UpdateBookUseCase getUpdateBookUseCaseBean(BookDao bookDao) {
        return new UpdateBookUseCase(bookDao);
    }

    @Bean
    public BookDao getBookDaoBean() {
        return new BookDao();
    }
}
