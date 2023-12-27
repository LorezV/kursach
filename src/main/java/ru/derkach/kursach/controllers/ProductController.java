package ru.derkach.kursach.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.derkach.kursach.models.Product;
import ru.derkach.kursach.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    private List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("")
    private Product create(@Valid @RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    private Product update(@PathVariable int id, @Valid @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
