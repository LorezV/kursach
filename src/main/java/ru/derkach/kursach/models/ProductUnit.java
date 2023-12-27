package ru.derkach.kursach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product_unit")
public class ProductUnit {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private LocalDate expiration_date;
}
