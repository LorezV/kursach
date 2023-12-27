package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
