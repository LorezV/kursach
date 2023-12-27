package ru.derkach.kursach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favourite")
public class Favourite {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int count;

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(optional = false)
    private Product product;
}
