package com.javatpoint.service;

import com.javatpoint.model.Author;
import com.javatpoint.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    public Author getAuthorById(int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional.orElse(null);
    }

    public void saveOrUpdate(Author author) {
        authorRepository.save(author);
    }

    public void delete(int id) {
        authorRepository.deleteById(id);
    }

    public void update(Author author, int id) {
        authorRepository.save(author);
    }
}
