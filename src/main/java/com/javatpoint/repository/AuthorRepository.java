package com.javatpoint.repository;

import org.springframework.data.repository.CrudRepository;
import com.javatpoint.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
