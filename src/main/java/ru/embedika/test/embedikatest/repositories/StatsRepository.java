package ru.embedika.test.embedikatest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.embedika.test.embedikatest.models.Stats;

public interface StatsRepository extends JpaRepository<Stats, Integer> {
}
