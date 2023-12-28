package ru.derkach.kursach.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.Product;
import ru.derkach.kursach.models.ProductUnit;
import ru.derkach.kursach.repositories.ProductRepository;
import ru.derkach.kursach.repositories.ProductUnitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductUnitService {
    @Autowired
    private ProductUnitRepository productUnitRepository;
    @Autowired
    private ProductRepository productRepository;
    private static final Random random = new Random();

    @Transactional
    public void generate(int maxStep) {
        List<ProductUnit> productUnits = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            int count = random.nextInt(10, 40);
            for (int i = 0; i < count; i++) {
                ProductUnit productUnit = new ProductUnit();
                productUnit.setProduct(product);
                productUnit.setExpirationStep(random.nextInt(maxStep / 2));
                productUnits.add(productUnit);
            }
        });

        productUnitRepository.saveAll(productUnits);
    }

    public List<ProductUnit> findAll() {
        return productUnitRepository.findAll();
    }

    public void saveAll(List<ProductUnit> productUnits) {
        productUnitRepository.saveAll(productUnits);
    }

    public void deleteAll() {
        productUnitRepository.deleteAll();
    }
}
