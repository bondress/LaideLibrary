package com.laide.LaideLibrary.service;

import com.laide.LaideLibrary.entities.Rental;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalService {
    Rental createRental(Rental rental);
    Rental getRental(long id);
    List<Rental> getAllRentals();
    Rental updateRental(long id, Rental rental);
    void deleteRental(long id);
}
