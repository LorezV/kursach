package ru.derkach.kursach.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.Product;
import ru.derkach.kursach.repositories.ProductRepository;
import ru.derkach.kursach.repositories.ProductUnitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll(Sort.by("id"));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(int id, Product product) {
        Product productDB = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if (Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())) {
            productDB.setName(product.getName());
        }

        productDB.setWeight(product.getWeight());
        productDB.setPrice(product.getPrice());
        productDB.setCategory(product.getCategory());

        return productRepository.save(productDB);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
