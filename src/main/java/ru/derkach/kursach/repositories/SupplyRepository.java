package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.Supply;

public interface SupplyRepository extends JpaRepository<Supply, Integer> {
}
