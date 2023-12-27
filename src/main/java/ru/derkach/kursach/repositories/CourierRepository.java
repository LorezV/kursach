package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.Courier;

public interface CourierRepository extends JpaRepository<Courier, Integer> {
}
