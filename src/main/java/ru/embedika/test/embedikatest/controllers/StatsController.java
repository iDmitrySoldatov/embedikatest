package ru.embedika.test.embedikatest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.embedika.test.embedikatest.dto.StatsDTO;
import ru.embedika.test.embedikatest.services.StatsService;

import java.util.List;

@RestController
public class StatsController {
    private StatsService service;

    @Autowired
    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public List<StatsDTO> getAllStats() {
        return service.findAll();
    }
}
