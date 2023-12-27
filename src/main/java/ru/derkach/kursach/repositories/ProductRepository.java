package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
