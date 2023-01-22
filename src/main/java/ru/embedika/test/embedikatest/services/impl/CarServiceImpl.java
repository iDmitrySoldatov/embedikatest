package ru.embedika.test.embedikatest.services.impl;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.embedika.test.embedikatest.dto.CarDTO;
import ru.embedika.test.embedikatest.dto.StatsDTO;
import ru.embedika.test.embedikatest.dto.convert.ConvertDTO;
import ru.embedika.test.embedikatest.exception.CarExistException;
import ru.embedika.test.embedikatest.exception.ResourceNotFoundException;
import ru.embedika.test.embedikatest.models.Car;
import ru.embedika.test.embedikatest.repositories.CarRepository;
import ru.embedika.test.embedikatest.services.CarService;
import ru.embedika.test.embedikatest.services.StatsService;
import springfox.documentation.annotations.Cacheable;

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
        return convertDTO.convertListToCarDTO(carList);
    }


    @Override
    @Timed("timeFindCarByID")
    @Cacheable(value = "findByID")
    public CarDTO findById(Integer id) {
        Optional<Car> optionalCar = repository.findById(id);
        if (optionalCar.isEmpty())
            throw new ResourceNotFoundException("Car not exist with id : " + id);

        return convertDTO.convertToCarDTO(optionalCar.get());
    }

    @Override
    @Transactional
    @Timed("timeCreateCar")
    @CachePut(value = "create")
    public CarDTO create(CarDTO carDTO) {
        Car oldCar = repository.findByNumber(carDTO.getNumber());
        if(oldCar != null)
            throw new CarExistException("A car with this number already exists");

        Car car = repository.save(convertDTO.convertToCar(carDTO));
        updateStatsNewCar(car);
        return convertDTO.convertToCarDTO(car);
    }

    @Override
    @Transactional
    @Timed("timeUpgradeCar")
    @CachePut(value = "upgrade")
    public CarDTO upgrade(CarDTO carDTO) {
        Optional<Car> oldCar = repository.findById(carDTO.getId());
        if (oldCar.isEmpty())
            throw new ResourceNotFoundException("Car not exist with id : " + carDTO.getId());

        Car updatedCar = repository.save(convertDTO.convertToCar(carDTO));
        updateStatsUpgradeCar(oldCar.get().getMileage(), updatedCar.getMileage());
        return convertDTO.convertToCarDTO(updatedCar);
    }

    @Override
    @Transactional
    @Timed("timeDeleteCar")
    public Boolean deleteById(Integer id) {
        Optional<Car> optionalCar = repository.findById(id);
        if (optionalCar.isEmpty())
            throw new ResourceNotFoundException("Car not exist with id : " + id);

        Car car = optionalCar.get();
        repository.deleteById(id);
        updateStatsDeleteCar(car);
        return true;
    }

    @Override
    @Timed("timeFindAllCarByOrderByPriceDesc")
    public List<CarDTO> findAllByOrderByPriceDesc() {
        List<Car> carList = repository.findAllByOrderByPriceDesc();
        return convertDTO.convertListToCarDTO(carList);
    }

    @Override
    @Timed("timeFindAllCarByOrderByPriceAsc")
    public List<CarDTO> findAllByOrderByPriceAsc() {
        List<Car> carList = repository.findAllByOrderByPriceAsc();
        return convertDTO.convertListToCarDTO(carList);
    }

    @Override
    @Timed("timeFindAllCarByOrderByMileageDesc")
    public List<CarDTO> findAllByOrderByMileageDesc() {
        List<Car> carList = repository.findAllByOrderByMileageDesc();
        return convertDTO.convertListToCarDTO(carList);
    }

    @Override
    @Timed("timeFindAllCarByOrderByMileageAsc")
    public List<CarDTO> findAllByOrderByMileageAsc() {
        List<Car> carList = repository.findAllByOrderByMileageAsc();
        return convertDTO.convertListToCarDTO(carList);
    }

    @Override
    @Timed("timeFindAllCarByOrderByYearDesc")
    public List<CarDTO> findAllByOrderByYearDesc() {
        List<Car> carList = repository.findAllByOrderByYearDesc();
        return convertDTO.convertListToCarDTO(carList);
    }

    @Override
    @Timed("timeFindAllCarByOrderByYearAsc")
    public List<CarDTO> findAllByOrderByYearAsc() {
        List<Car> carList = repository.findAllByOrderByYearAsc();
        return convertDTO.convertListToCarDTO(carList);
    }

    @Override
    @Timed("timeFindCarByNumber")
    @Cacheable(value = "findByNumberName")
    public CarDTO findByNumber(String number) {
        Car car = repository.findByNumber(number);
        return car != null ? convertDTO.convertToCarDTO(car) : null;
    }

    private void updateStatsNewCar(Car car) {
        StatsDTO statsDTO = getStatsDTO();
        statsDTO.setCountAllCars(statsDTO.getCountAllCars() + 1);
        if (car.getMileage() == 0)
            statsDTO.setCountNewCars(statsDTO.getCountNewCars() + 1);

        statsService.save(statsDTO);
    }

    private void updateStatsUpgradeCar(int oldMileage, int newMileage) {
        if (oldMileage > 0 || newMileage == 0) return;

        StatsDTO statsDTO = getStatsDTO();
        statsDTO.setCountNewCars(statsDTO.getCountNewCars() - 1);
        statsService.save(statsDTO);
    }

    private void updateStatsDeleteCar(Car car) {
        StatsDTO statsDTO = getStatsDTO();
        statsDTO.setCountAllCars(statsDTO.getCountAllCars() - 1);
        if (car.getMileage() == 0)
            statsDTO.setCountNewCars(statsDTO.getCountNewCars() - 1);

        statsService.save(statsDTO);
    }

    private StatsDTO getStatsDTO() {
        StatsDTO statsDTO = statsService.findById(1);
        if (statsDTO == null) {
            statsDTO = new StatsDTO();
            statsDTO.setId(1);
        }
        return statsDTO;
    }
}



