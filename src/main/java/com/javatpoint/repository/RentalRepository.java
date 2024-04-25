package com.javatpoint.repository;

import com.javatpoint.model.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface RentalRepository extends CrudRepository<Rental, Integer> {

    Optional<Rental> findByBookId(int bookId);

    List<Rental> findByRentalDateBeforeAndReturnDateIsNull(Date date);

    List<Rental> findByReturnDateBeforeAndReturnDateNotNull(Date date);
}
