package com.javatpoint.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javatpoint.model.Books;
import com.javatpoint.service.BooksService;

@RestController
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books")
    private List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    private Books getBook(@PathVariable("id") int id) {
        return booksService.getBooksById(id);
    }

    @DeleteMapping("/books/{id}")
    private void deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);
    }

    @PostMapping("/books")
    private int saveBook(@RequestBody Books books) {
        booksService.saveOrUpdate(books);
        return books.getId();
    }

    @PutMapping("/books")
    private Books updateBook(@RequestBody Books books) {
        booksService.saveOrUpdate(books);
        return books;
    }
}
