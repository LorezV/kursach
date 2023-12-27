package ru.derkach.kursach.dto.simulation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimulationStartDto {
    @NotNull
    private int duration;

    @NotNull
    private int step;

    @Min(15)
    @Max(25)
    private int couriers;
}
