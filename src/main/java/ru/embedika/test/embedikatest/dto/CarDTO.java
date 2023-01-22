package ru.embedika.test.embedikatest.dto;


import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CarDTO {
    private Integer id;
    @Pattern(regexp = "^[АВЕКМНОРСТУХ]{2}\\d{3}[АВЕКМНОРСТУХ]\\d{2,3}", message = "Введите регистрационный знак машины по ГОСТу")
    private String number;
    @Size(min = 3, max = 32, message = "Введите от 3 до 32 символов")
    private String brand;
    @NotBlank
    private String colour;
    @Pattern(regexp = "((19|20)\\d\\d)", message = "Введите год в верном формате")
    private String year;
    @PositiveOrZero(message = "Введите положительное число")
    private int mileage;
    @Positive(message = "Введите положительное число")
    private long price;
}
