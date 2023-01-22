package ru.embedika.test.embedikatest.models;


import lombok.Data;

import javax.persistence.*;

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
    private String year;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "price")
    private long price;
}
