package ru.derkach.kursach.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.derkach.kursach.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
