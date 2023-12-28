package ru.derkach.kursach.services;

import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.*;
import ru.derkach.kursach.repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductUnitRepository productUnitRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private static final Random random = new Random();

    public List<Order> findAll() {
        return orderRepository.findAll(Sort.by("id")).stream().sorted((a, b) -> b.getId() - a.getId()).toList();
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Transactional
    public void generate() {
        List<Customer> customers = customerRepository.findAll();

        customers.forEach(customer -> {
            List<Product> products = productRepository.findAll();
            if (products.isEmpty()) {
                return;
            }

            List<OrderProduct> orderProducts = new ArrayList<>();
            List<Product> productsForOrder = products.stream().filter(product -> !product.getProductUnits().isEmpty() & random.nextBoolean()).toList();
            if (!productsForOrder.isEmpty()) {
                productsForOrder.forEach(product -> {
                    OrderProduct orderProduct = new OrderProduct();
                    if (product.getProductUnits().size() == 1) {
                        orderProduct.setQuantity(1);
                    } else {
                        orderProduct.setQuantity(random.nextInt(1, product.getProductUnits().size()));
                    }
                    orderProduct.setProduct(product);
                    orderProducts.add(orderProduct);
                });
            }

            float price = 0F;
            for (int i = 0; i < orderProducts.size(); i++) {
                OrderProduct orderProduct = orderProducts.get(i);
                price += orderProduct.getQuantity() * orderProduct.getProduct().getPrice();
            }

            price *= 1.25F;
            if (Objects.nonNull(customer.getDiscountCard())) {
                price *= 0.95F;
            } else {
                if (price > 1000) {
                    price *= 0.97F;
                }
            }

            if (!orderProducts.isEmpty()) {
                Order order = new Order();
                order.setCustomer(customer);
                order.setReady(false);
                order.setPrice(Math.round(price * 100F) / 100F);
                order.setOrderProducts(orderProducts);

                Order dbOrder = orderRepository.save(order);
                orderProducts.forEach(orderProduct -> {
                    orderProduct.setOrder(dbOrder);
                });
                orderProductRepository.saveAll(orderProducts);
            }
        });
    }
}
