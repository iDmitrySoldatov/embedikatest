package ru.embedika.test.embedikatest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.embedika.test.embedikatest.dto.CarDTO;
import ru.embedika.test.embedikatest.dto.convert.ConvertDTO;
import ru.embedika.test.embedikatest.models.Car;
import ru.embedika.test.embedikatest.repositories.CarRepository;
import ru.embedika.test.embedikatest.services.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private CarRepository repository;
    private ConvertDTO convertDTO;
    @Autowired
    public CarServiceImpl(CarRepository repository, ConvertDTO convertDTO) {
        this.repository = repository;
        this.convertDTO = convertDTO;
    }
    @Override
    public List<CarDTO> findAll() {
        List<Car> carList = repository.findAll();
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for(Car car : carList) {
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    public CarDTO findById(Integer id) {
        Optional<Car> optionalCar = repository.findById(id);
        if(!optionalCar.isEmpty()) {
            Car car = optionalCar.get();
            CarDTO carDTO = convertDTO.convertToCarDTO(car);
            return carDTO;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public CarDTO save(CarDTO carDTO) {
        Car car = repository.save(convertDTO.convertToCar(carDTO));
        return convertDTO.convertToCarDTO(car);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
