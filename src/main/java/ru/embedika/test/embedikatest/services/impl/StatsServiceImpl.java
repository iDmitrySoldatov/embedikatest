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
        return convertDTO.convertListToStatsDTO(repository.findAll());
    }

    @Override
    @Transactional
    @Timed("timeSaveStats")
    public StatsDTO save(StatsDTO statsDTO) {
        return convertDTO.convertToStatsDTO(repository.save(convertDTO.convertToStats(statsDTO)));
    }

    @Override
    @Timed("timeFindByIdStats")
    public StatsDTO findById(Integer id) {
        Optional<Stats> optionalStats = repository.findById(id);
        return !optionalStats.isEmpty() ? convertDTO.convertToStatsDTO(optionalStats.get()) : null;
    }
}
