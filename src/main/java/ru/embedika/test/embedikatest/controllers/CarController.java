package ru.embedika.test.embedikatest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.embedika.test.embedikatest.dto.CarDTO;
import ru.embedika.test.embedikatest.exception.CarExistException;
import ru.embedika.test.embedikatest.exception.ResourceNotFoundException;
import ru.embedika.test.embedikatest.services.CarService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {
    private CarService service;

    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/car")
    public List<CarDTO> getAllCar() {
        return service.findAll();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Integer id) {
        CarDTO carDTO = service.findById(id);
        if (carDTO == null) {
            throw new ResourceNotFoundException("Car not exist with id : " + id);
        } else {
            return ResponseEntity.ok(carDTO);
        }
    }

    @PostMapping("/car")
    public CarDTO createCar(@RequestBody @Valid CarDTO carDTO) {
        CarDTO car = service.findByNumber(carDTO.getNumber());
        if (car != null) {
            throw new CarExistException("A car with this number already exists");
        } else {
            return service.save(carDTO);
        }
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Integer id, @RequestBody CarDTO carDTO) {
        CarDTO car = service.findById(id);
        if (car == null) {
            throw new ResourceNotFoundException("Car not exist with id : " + id);
        } else {
            car.setNumber(carDTO.getNumber());
            car.setBrand(carDTO.getBrand());
            car.setColour(carDTO.getColour());
            car.setYear(carDTO.getYear());
            car.setMileage(carDTO.getMileage());
            car.setPrice(carDTO.getPrice());
            CarDTO updateCar = service.save(car);
            return ResponseEntity.ok(updateCar);
        }
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCar(@PathVariable Integer id) {
        CarDTO car = service.findById(id);
        if (car == null) {
            throw new ResourceNotFoundException("Car not exist with id : " + id);
        } else {
            service.deleteById(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("delete", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/carByOrderByPriceDesc")
    public List<CarDTO> getAllByOrderByPriceDesc() {
        return service.findAllByOrderByPriceDesc();
    }

    @GetMapping("/carByOrderByPriceAsc")
    public List<CarDTO> getAllByOrderByPriceAsc() {
        return service.findAllByOrderByPriceAsc();
    }

    @GetMapping("/carByOrderByMileageDesc")
    public List<CarDTO> getAllByOrderByMileageDesc() {
        return service.findAllByOrderByMileageDesc();
    }

    @GetMapping("/carByOrderByMileageAsc")
    public List<CarDTO> getAllByOrderByMileageAsc() {
        return service.findAllByOrderByMileageAsc();
    }

    @GetMapping("/carByOrderByYearDesc")
    public List<CarDTO> getAllByOrderByYearDesc() {
        return service.findAllByOrderByYearDesc();
    }

    @GetMapping("/carByOrderByYearAsc")
    public List<CarDTO> getAllByOrderByYearAsc() {
        return service.findAllByOrderByYearAsc();
    }
}
