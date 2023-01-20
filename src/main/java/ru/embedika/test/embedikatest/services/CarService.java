package ru.embedika.test.embedikatest.services;

import io.micrometer.core.annotation.Timed;
import ru.embedika.test.embedikatest.dto.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> findAll();

    CarDTO findById(Integer id);

    CarDTO save(CarDTO carDTO);

    void deleteById(Integer id);

    List<CarDTO> findAllByOrderByPriceDesc();

    List<CarDTO> findAllByOrderByPriceAsc();

    List<CarDTO> findAllByOrderByMileageDesc();

    List<CarDTO> findAllByOrderByMileageAsc();

    List<CarDTO> findAllByOrderByYearDesc();

    List<CarDTO> findAllByOrderByYearAsc();

    CarDTO findByNumber(String number);
}
