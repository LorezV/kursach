package ru.derkach.kursach.models;

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

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "finished_at")
    private LocalDateTime finishedAt;

    @ManyToOne(optional = false)
    private Product product;
}
