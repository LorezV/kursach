package ru.derkach.kursach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.Category;
import ru.derkach.kursach.repositories.CategoryRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll(Sort.by("id"));
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(int id, Category category) {
        Optional<Category> candidate = categoryRepository.findById(id);
        if (candidate.isPresent()) {
            Category categoryDB = candidate.get();

            if (Objects.nonNull(category.getName()) && !"".equalsIgnoreCase(category.getName())) {
                categoryDB.setName(category.getName());
            }

            return categoryRepository.save(categoryDB);
        }

        return null;
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}
