package ru.derkach.kursach.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.derkach.kursach.dto.simulation.SimulationDto;
import ru.derkach.kursach.dto.simulation.SimulationStartDto;
import ru.derkach.kursach.services.SimulationService;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
    @Autowired
    private SimulationService simulationService;

    @GetMapping("/status")
    public SimulationDto status() {
        return new SimulationDto(simulationService.isRunning());
    }

    @PostMapping("/start")
    public void start(@Valid @RequestBody SimulationStartDto data) {
        simulationService.start(data.getStep(), data.getDuration());
    }

    @PostMapping("/stop")
    public void stop() {
        simulationService.stop();
    }
}
