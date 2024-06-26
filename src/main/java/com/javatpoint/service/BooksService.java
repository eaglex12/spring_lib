package com.javatpoint.service;

import com.javatpoint.model.Books;
import com.javatpoint.model.Rental;
import com.javatpoint.repository.BooksRepository;
import com.javatpoint.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;




@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public List<Books> getAllBooks() {
        List<Books> books = new ArrayList<>();
        booksRepository.findAll().forEach(books::add);
        return books;
    }

    public Books getBooksById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public void saveOrUpdate(Books books) {
        booksRepository.save(books);
    }

    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public boolean returnBook(int rentalId, LocalDate returnDate) {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            rental.setReturnDate(returnDate);
            rentalRepository.save(rental);
            updateBookAvailability(rental.getBookId(), "available");
            return true;
        }
        return false;
    }
    public void updateBookAvailability(int bookId, String availability) {
        Optional<Books> bookOptional = booksRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            Books book = bookOptional.get();
            book.setAvailability(availability);
            booksRepository.save(book);
        }
    }
    public boolean isBookAvailable(int bookId) {
        Books book = booksRepository.findById(bookId).orElse(null);
        return book != null && "available".equals(book.getAvailability());
    }
    
    


    public List<Rental> getOverdueRentals() {
        LocalDate currentDate = LocalDate.now();
        Date overdueDate = java.sql.Date.valueOf(currentDate.minusDays(14));
        return rentalRepository.findByRentalDateBeforeAndReturnDateIsNull(overdueDate);
    }
    
}