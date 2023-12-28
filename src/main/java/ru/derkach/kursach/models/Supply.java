package ru.derkach.kursach.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "supply")
public class Supply {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false, name = "finished_step")
    private int finishStep;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JsonIgnore
    private Product product;
}
