package ru.embedika.test.embedikatest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.embedika.test.embedikatest.models.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
