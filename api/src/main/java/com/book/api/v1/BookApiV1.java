package com.book.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${spring.application.api-v1}")
public class BookApiV1 {

    @GetMapping(path = "/books")
    public String getBooks(){
        return "";
    }
}
