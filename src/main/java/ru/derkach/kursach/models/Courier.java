package ru.derkach.kursach.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;
}
