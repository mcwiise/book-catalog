package com.book.api.v1;

import com.book.api.v1.vo.BookVO;
import com.book.usecase.SimpleUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${spring.application.api-v1}")
public class BookApiV1 {

    private SimpleUseCase<List<BookVO>> retrieveBooksUseCase;

    @Autowired
    public BookApiV1(SimpleUseCase<List<BookVO>> retrieveBooksUseCase) {
        this.retrieveBooksUseCase = retrieveBooksUseCase;
    }

    @GetMapping(path = "/books")
    public List<BookVO> getBooks(){
        return this.retrieveBooksUseCase.exe();
    }
}
