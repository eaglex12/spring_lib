package com.javatpoint.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javatpoint.model.Author;
import com.javatpoint.service.AuthorService;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/authors")
    private List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    private Author getAuthor(@PathVariable("id") int id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/authors/{id}")
    private void deleteAuthor(@PathVariable("id") int id) {
        authorService.delete(id);
    }

    @PostMapping("/authors")
    private int saveAuthor(@RequestBody Author author) {
        authorService.saveOrUpdate(author);
        return author.getId();
    }

    @PutMapping("/authors")
    private Author updateAuthor(@RequestBody Author author) {
        authorService.saveOrUpdate(author);
        return author;
    }
}
