package ru.derkach.kursach.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "courier")
public class Courier {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "courier")
    private List<Order> orders;
}
