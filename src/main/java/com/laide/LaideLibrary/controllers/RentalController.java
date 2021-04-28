package com.laide.LaideLibrary.controllers;

import com.laide.LaideLibrary.entities.Rental;
import com.laide.LaideLibrary.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/addRental")
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental){
        Rental createdRental = rentalService.createRental(rental);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdRental.getRentalId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/getRentalById/{id}")
    public ResponseEntity<Rental> getRental(@PathVariable("id") long id){
        Rental rental = rentalService.getRental(id);
        return ResponseEntity.ok(rental);
    }

    @GetMapping("/allRentals")
    public ResponseEntity<List<Rental>> getAllRentals(){
        List<Rental> rentalList = rentalService.getAllRentals();
        return ResponseEntity.ok(rentalList);
    }

    @PutMapping("/updateRentalById/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable("id") long id, @RequestBody Rental rental){
        Rental updateRental = rentalService.updateRental(id, rental);
        return ResponseEntity.ok(updateRental);
    }

    @DeleteMapping("/deleteRentalById/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable("id") long id){
        rentalService.deleteRental(id);
        return ResponseEntity.ok().build();
    }
}
