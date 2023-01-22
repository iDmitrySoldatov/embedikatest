package ru.embedika.test.embedikatest.dto.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.embedika.test.embedikatest.dto.CarDTO;
import ru.embedika.test.embedikatest.dto.StatsDTO;
import ru.embedika.test.embedikatest.models.Car;
import ru.embedika.test.embedikatest.models.Stats;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertDTO {

    private ModelMapper modelMapper;

    @Autowired
    public ConvertDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarDTO convertToCarDTO(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    public Car convertToCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    public List<CarDTO> convertListToCarDTO(List<Car> carList) {
        List<CarDTO> carDTOList = new ArrayList<>(carList.size());
        for (Car car : carList) {
            CarDTO carDTO = this.convertToCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    public StatsDTO convertToStatsDTO(Stats stats) {
        return modelMapper.map(stats, StatsDTO.class);
    }

    public Stats convertToStats(StatsDTO statsDTO) {
        return modelMapper.map(statsDTO, Stats.class);
    }

    public List<StatsDTO> convertListToStatsDTO(List<Stats> statsList) {
        List<StatsDTO> statsDTOList = new ArrayList<>(statsList.size());
        for (Stats stats : statsList) {
            StatsDTO statsDTO = this.convertToStatsDTO(stats);
            statsDTOList.add(statsDTO);
        }
        return statsDTOList;
    }
}
