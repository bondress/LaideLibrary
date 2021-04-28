package com.laide.LaideLibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentalId;

    @Column(name = "userId")
    private long userId;

    @Column(name = "bookId")
    private long bookId;

    @Column(name = "returned")
    private int returned;
}
