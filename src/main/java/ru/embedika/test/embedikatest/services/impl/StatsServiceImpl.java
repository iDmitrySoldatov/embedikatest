package ru.embedika.test.embedikatest.services.impl;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.embedika.test.embedikatest.dto.StatsDTO;
import ru.embedika.test.embedikatest.dto.convert.ConvertDTO;
import ru.embedika.test.embedikatest.models.Stats;
import ru.embedika.test.embedikatest.repositories.StatsRepository;
import ru.embedika.test.embedikatest.services.StatsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StatsServiceImpl implements StatsService {
    private StatsRepository repository;

    private ConvertDTO convertDTO;

    @Autowired
    public StatsServiceImpl(StatsRepository repository, ConvertDTO convertDTO) {
        this.repository = repository;
        this.convertDTO = convertDTO;
    }

    @Override
    @Timed("timeFindAllStats")
    public List<StatsDTO> findAll() {
        List<Stats> statsList = repository.findAll();
        List<StatsDTO> statsDTOList = new ArrayList<>();
        for (Stats stats : statsList) {
            StatsDTO statsDTO = convertDTO.convertToStatsDTO(stats);
            statsDTOList.add(statsDTO);
        }
        return statsDTOList;
    }

    @Override
    @Transactional
    @Timed("timeSaveStats")
    public StatsDTO save(StatsDTO statsDTO) {
        Stats stats = repository.save(convertDTO.convertToStats(statsDTO));
        return convertDTO.convertToStatsDTO(stats);
    }

    @Override
    @Timed("timeFindByIdStats")
    public StatsDTO findById(Integer id) {
        Optional<Stats> optionalStats = repository.findById(id);
        if (!optionalStats.isEmpty()) {
            Stats stats = optionalStats.get();
            StatsDTO statsDTO = convertDTO.convertToStatsDTO(stats);
            return statsDTO;
        } else {
            return null;
        }
    }
}
