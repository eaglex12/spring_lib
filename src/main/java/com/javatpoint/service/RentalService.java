package com.javatpoint.service;

import com.javatpoint.model.Rental;
import com.javatpoint.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.sql.Date;  
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentalRepository.findAll().forEach(rentals::add);
        return rentals;
    }

    public Rental getRentalById(int id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        return rentalOptional.orElse(null);
    }

    public void saveOrUpdate(Rental rental) {
        rentalRepository.save(rental);
    }

    public void delete(int id) {
        rentalRepository.deleteById(id);
    }

    public boolean rentBook(Rental rental) {
        Optional<Rental> existingRental = rentalRepository.findByBookId(rental.getBookId());
        if (existingRental.isEmpty()) {
            rentalRepository.save(rental);
            return true;
        }
        return false;
    }


    public boolean returnBook(int rentalId, LocalDate returnDate) {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            rental.setReturnDate(returnDate);  // Directly setting LocalDate
            rentalRepository.save(rental);
            return true;
        }
        return false;
    }
    
    


    public List<Rental> getOverdueRentals() {
        List<Rental> allRentals = getAllRentals();
        List<Rental> overdueRentals = new ArrayList<>();
        
        for (Rental rental : allRentals) {
            if (rental.getReturnDate() != null && rental.getRentalDate() != null) {
                LocalDate rentalDate = rental.getRentalDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate actualReturnDate = rental.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                
                long daysBetween = ChronoUnit.DAYS.between(rentalDate, actualReturnDate);
                if (daysBetween > 14) {
                    overdueRentals.add(rental);
                }
            }
        }
        
        return overdueRentals;
    }
    
}
