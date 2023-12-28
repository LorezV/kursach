package ru.derkach.kursach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.derkach.kursach.models.Courier;
import ru.derkach.kursach.services.CourierService;

import java.util.List;

@RestController
@RequestMapping("/courier")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @GetMapping("")
    public List<Courier> findAll() {
        return courierService.findAll();
    }
}
