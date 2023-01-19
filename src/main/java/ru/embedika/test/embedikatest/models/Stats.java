package ru.embedika.test.embedikatest.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "Stats")
@Data
public class Stats {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "count_all_cars")
    private Integer countAllCars;
    @Column(name = "count_new_cars")
    private Integer countNewCars;


}
