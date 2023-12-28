package ru.derkach.kursach.dto.simulation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimulationStatusDto {
    private boolean status;

    private int duration;

    private int steps;

    private int couriers;

    private int step;
}
