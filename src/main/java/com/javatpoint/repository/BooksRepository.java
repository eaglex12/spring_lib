package com.javatpoint.repository;
import org.springframework.data.repository.CrudRepository;

import com.javatpoint.model.Books;
public interface BooksRepository extends CrudRepository<Books, Integer>
{
}
