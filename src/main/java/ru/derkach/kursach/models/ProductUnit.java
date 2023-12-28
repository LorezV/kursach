package ru.derkach.kursach.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Product product;

    @Column(name = "expiration_step", nullable = false)
    private int expirationStep;
}
