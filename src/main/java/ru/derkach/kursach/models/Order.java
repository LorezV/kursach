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
@Entity(name = "_order")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private boolean ready;

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Courier courier;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;
}
