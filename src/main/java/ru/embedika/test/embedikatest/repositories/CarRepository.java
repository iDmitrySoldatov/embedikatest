package ru.embedika.test.embedikatest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.embedika.test.embedikatest.models.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByOrderByPriceDesc();

    List<Car> findAllByOrderByPriceAsc();

    List<Car> findAllByOrderByMileageDesc();

    List<Car> findAllByOrderByMileageAsc();

    List<Car> findAllByOrderByYearDesc();

    List<Car> findAllByOrderByYearAsc();

    Car findByNumber(String number);
}
