package ru.embedika.test.embedikatest.services;

import ru.embedika.test.embedikatest.dto.StatsDTO;

import java.util.List;

public interface StatsService {
    List<StatsDTO> findAll();

    StatsDTO save(StatsDTO statsDTO);

    StatsDTO findById(Integer id);
}
