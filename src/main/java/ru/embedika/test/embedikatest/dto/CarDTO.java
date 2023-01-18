package ru.embedika.test.embedikatest.dto;


import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    private String number;
    private String brand;
    private String colour;
    private Integer year;
    private Integer mileage;
    private Long price;
}
