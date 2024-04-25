package com.javatpoint.controller;

import com.javatpoint.model.Rental;
import com.javatpoint.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @GetMapping
    private List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    private Rental getRental(@PathVariable("id") int id) {
        return rentalService.getRentalById(id);
    }

    @DeleteMapping("/{id}")
    private void deleteRental(@PathVariable("id") int id) {
        rentalService.delete(id);
    }

    @PostMapping
    private ResponseEntity<String> rentBook(@RequestBody Rental rental) {
        boolean result = rentalService.rentBook(rental);
        if (result) {
            return new ResponseEntity<>("Book rented successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book is already rented!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/return/{rentalId}")
public ResponseEntity<String> returnBook(@PathVariable int rentalId, @RequestBody Map<String, String> requestBody) {
    String returnDateString = requestBody.get("returnDate");
    
    if (returnDateString == null || returnDateString.isEmpty()) {
        return new ResponseEntity<>("returnDate is required!", HttpStatus.BAD_REQUEST);
    }

    LocalDate returnDate = LocalDate.parse(returnDateString);
    boolean result = rentalService.returnBook(rentalId, returnDate);
    
    if (result) {
        return new ResponseEntity<>("Book returned successfully!", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Failed to return book!", HttpStatus.BAD_REQUEST);
    }
}

    

    

    @GetMapping("/overdue")
    public ResponseEntity<List<Rental>> getOverdueRentals() {
        List<Rental> overdueRentals = rentalService.getOverdueRentals();
        return new ResponseEntity<>(overdueRentals, HttpStatus.OK);
    }
}
