package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.ProductUnit;

public interface ProductUnitRepository extends JpaRepository<ProductUnit, Integer> {
}
