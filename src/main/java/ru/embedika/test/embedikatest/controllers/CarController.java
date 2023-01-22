package ru.embedika.test.embedikatest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.embedika.test.embedikatest.dto.CarDTO;
import ru.embedika.test.embedikatest.services.CarService;

import javax.validation.Valid;
import java.util.List;

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
    public CarDTO getCar(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("/car")
    public CarDTO createCar(@RequestBody @Valid CarDTO carDTO) {
        return service.create(carDTO);
    }

    @PutMapping("/car/{id}")
    public CarDTO upgradeCar(@PathVariable Integer id, @RequestBody @Valid CarDTO carDTO) {
        carDTO.setId(id);
        return service.upgrade(carDTO);
    }

    @DeleteMapping("/car/{id}")
    public Boolean deleteCar(@PathVariable Integer id) {
        return service.deleteById(id);
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
