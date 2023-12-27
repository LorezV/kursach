package ru.derkach.kursach.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.derkach.kursach.models.Category;
import ru.derkach.kursach.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    private List<Category> findAll() {
        return categoryService.findAll();
    }

    @PostMapping("")
    private Category create(@Valid @RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    private Category update(@PathVariable int id, @Valid @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
