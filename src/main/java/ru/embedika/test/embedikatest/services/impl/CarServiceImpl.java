package ru.embedika.test.embedikatest.services.impl;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.embedika.test.embedikatest.dto.CarDTO;
import ru.embedika.test.embedikatest.dto.StatsDTO;
import ru.embedika.test.embedikatest.dto.convert.ConvertDTO;
import ru.embedika.test.embedikatest.models.Car;
import ru.embedika.test.embedikatest.repositories.CarRepository;
import ru.embedika.test.embedikatest.services.CarService;
import ru.embedika.test.embedikatest.services.StatsService;
import springfox.documentation.annotations.Cacheable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    private StatsService statsService;
    private ConvertDTO convertDTO;

    @Autowired
    public CarServiceImpl(CarRepository repository, StatsService statsService, ConvertDTO convertDTO) {
        this.repository = repository;
        this.statsService = statsService;
        this.convertDTO = convertDTO;
    }

    @Override
    @Timed("timeFindAllCar")
    public List<CarDTO> findAll() {
        List<Car> carList = repository.findAll();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }


    @Override
    @Timed("timeFindCarByID")
    @Cacheable(value = "findByID")
    public CarDTO findById(Integer id) {
        Optional<Car> optionalCar = repository.findById(id);
        if (!optionalCar.isEmpty()) {
            Car car = optionalCar.get();
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            return carDTO;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    @Timed("timeSaveCar")
    @CachePut(value = "save")
    public CarDTO save(CarDTO carDTO) {
        Car car = repository.save(convertDTO.convertToCar(carDTO));
        StatsDTO statsDTO = statsService.findById(1);
        if (statsDTO == null) {
            statsDTO = new StatsDTO();
            statsDTO.setId(1);
            statsDTO.setCountAllCars(1);
            if (car.getMileage() == 0) statsDTO.setCountNewCars(1);
            else statsDTO.setCountNewCars(0);
            statsService.save(statsDTO);
            return convertDTO.convertToCarDTO(car);
        } else {
            statsDTO.setCountAllCars(statsDTO.getCountAllCars() + 1);
            if (car.getMileage() == 0) statsDTO.setCountNewCars(statsDTO.getCountNewCars() + 1);
            statsService.save(statsDTO);
            return convertDTO.convertToCarDTO(car);
        }
    }

    @Override
    @Transactional
    @Timed("timeDeleteCar")
    public void deleteById(Integer id) {
        Optional<Car> optionalCar = repository.findById(id);
        if (!optionalCar.isEmpty()) {
            Car car = optionalCar.get();
            StatsDTO statsDTO = statsService.findById(1);
            statsDTO.setCountAllCars(statsDTO.getCountAllCars() - 1);
            if (car.getMileage() == 0) statsDTO.setCountNewCars(statsDTO.getCountNewCars() - 1);
            statsService.save(statsDTO);
            repository.deleteById(id);
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    @Timed("timeFindAllCarByOrderByPriceDesc")
    public List<CarDTO> findAllByOrderByPriceDesc() {
        List<Car> carList = repository.findAllByOrderByPriceDesc();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    @Timed("timeFindAllCarByOrderByPriceAsc")
    public List<CarDTO> findAllByOrderByPriceAsc() {
        List<Car> carList = repository.findAllByOrderByPriceAsc();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    @Timed("timeFindAllCarByOrderByMileageDesc")
    public List<CarDTO> findAllByOrderByMileageDesc() {
        List<Car> carList = repository.findAllByOrderByMileageDesc();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    @Timed("timeFindAllCarByOrderByMileageAsc")
    public List<CarDTO> findAllByOrderByMileageAsc() {
        List<Car> carList = repository.findAllByOrderByMileageAsc();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    @Timed("timeFindAllCarByOrderByYearDesc")
    public List<CarDTO> findAllByOrderByYearDesc() {
        List<Car> carList = repository.findAllByOrderByYearDesc();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    @Timed("timeFindAllCarByOrderByYearAsc")
    public List<CarDTO> findAllByOrderByYearAsc() {
        List<Car> carList = repository.findAllByOrderByYearAsc();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    @Timed("timeFindCarByNumber")
    @Cacheable(value = "findByNumberName")
    public CarDTO findByNumber(String number) {
        Car car = repository.findByNumber(number);
        if (car != null) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            return carDTO;
        } else {
            return null;
        }
    }
}
