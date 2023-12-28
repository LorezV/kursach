package ru.derkach.kursach.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Order order;

    @ManyToOne(optional = false)
    private Product product;
}
