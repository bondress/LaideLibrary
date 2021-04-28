package com.laide.LaideLibrary.service.serviceImpl;

import com.laide.LaideLibrary.entities.Rental;
import com.laide.LaideLibrary.exception.ResourceNotFoundException;
import com.laide.LaideLibrary.repository.RentalRepository;
import com.laide.LaideLibrary.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public Rental createRental(Rental rental) {

        return rentalRepository.save(rental);
    }

    @Override
    public Rental getRental(long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if(!optionalRental.isPresent()){
            throw new ResourceNotFoundException("Rental with id " + id + " is not found");
        }
        return optionalRental.get();
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental updateRental(long id, Rental rental) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if(!optionalRental.isPresent()){
            throw new ResourceNotFoundException("Rental with id " + id + " is not found");
        }
        rental.setRentalId(id);
        return rentalRepository.save(rental);
    }

    @Override
    public void deleteRental(long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if(!optionalRental.isPresent()){
            throw new ResourceNotFoundException("Rental with id " + id + " is not found");
        }
        rentalRepository.deleteById(id);
    }
}
