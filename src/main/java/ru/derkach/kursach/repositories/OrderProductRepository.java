package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}
