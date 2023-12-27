package ru.derkach.kursach.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.Category;
import ru.derkach.kursach.models.Product;
import ru.derkach.kursach.repositories.CategoryRepository;
import ru.derkach.kursach.repositories.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll(Sort.by("id"));
    }

    public Product create(Product product) {
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product update(int id, Product product) {
        Product productDB = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        System.out.println(product.getWeight());

        if (Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())) {
            productDB.setName(product.getName());
        }

        productDB.setWeight(product.getWeight());
        productDB.setPrice(product.getPrice());

        if (Objects.nonNull(product.getCategory())) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            productDB.setCategory(category);
        }

        return productRepository.save(productDB);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
