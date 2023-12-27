package ru.derkach.kursach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float weight;

    @Column(nullable = false)
    private float price;

    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductUnit> productUnits;

    @OneToMany(mappedBy = "product")
    private List<Favourite> favourites;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;
}
