package ru.embedika.test.embedikatest.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "Car")
@Data
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "brand")
    private String brand;

    @Column(name = "colour")
    private String colour;

    @Column(name = "year")
    private Integer year;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "price")
    private Long price;
}
