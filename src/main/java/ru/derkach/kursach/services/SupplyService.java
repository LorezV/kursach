package ru.derkach.kursach.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.Product;
import ru.derkach.kursach.models.ProductUnit;
import ru.derkach.kursach.models.Supply;
import ru.derkach.kursach.repositories.ProductRepository;
import ru.derkach.kursach.repositories.ProductUnitRepository;
import ru.derkach.kursach.repositories.SupplyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SupplyService {
    @Autowired
    private SupplyRepository supplyRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductUnitRepository productUnitRepository;

    private static final Random random = new Random();

    public List<Supply> findAll() {
        return supplyRepository.findAll();
    }

    public void deleteAll() {
        this.supplyRepository.deleteAll();
    }

    public void supply(int steps, int duration) {
        List<Product> products = productRepository.findAll();

        List<Product> endingProducts = products.stream().filter(product -> product.getProductUnits().size() < 10).toList();
        endingProducts.forEach(product -> {
            Supply supply = new Supply();
            supply.setProduct(product);
            supply.setCount(random.nextInt(40, 60));
            supply.setFinishStep(random.nextInt(steps, duration));
            supplyRepository.save(supply);
        });

        List<ProductUnit> endingProductUnits = productUnitRepository.findAll().stream().filter(productUnit -> productUnit.getExpirationStep() <= steps).toList();
        productUnitRepository.deleteAllInBatch(endingProductUnits);

        List<Supply> supplies = supplyRepository.findAll();
        List<Supply> finishedSupplies= supplies.stream().filter(supply -> supply.getFinishStep() <= steps).toList();
        finishedSupplies.forEach(supply -> {
            List<ProductUnit> productUnits = new ArrayList<>();
            for (int i = 0; i < supply.getCount(); i++) {
                ProductUnit productUnit = new ProductUnit();
                productUnit.setExpirationStep(random.nextInt(steps, duration));
                productUnit.setProduct(supply.getProduct());
                productUnits.add(productUnit);
            }

            productUnitRepository.saveAll(productUnits);
        });
    }
}
