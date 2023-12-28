package ru.derkach.kursach.services;

import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.Courier;
import ru.derkach.kursach.models.Order;
import ru.derkach.kursach.models.OrderProduct;
import ru.derkach.kursach.repositories.CourierRepository;
import ru.derkach.kursach.repositories.OrderRepository;

import java.util.*;

@Service
public class CourierService {
    @Autowired
    private CourierRepository courierRepository;

    public List<Courier> findAll() {
        return courierRepository.findAll(Sort.by("id"));
    }

    @Transactional
    public void generate(int count) {
        ArrayList<Courier> couriers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Courier courier = new Courier();
            courier.setName(generateName());
            couriers.add(courier);
        }

        courierRepository.saveAll(couriers);
    }

    @Transactional
    public void deleteAll() {
        courierRepository.deleteAllInBatch();
    }

    private final static String[] FIRSTNAMES = {"Петя", "Вася", "Слава", "Паша", "Ваня", "Артём"};
    private final static String[] LASTNAMES = {"Иванов", "Петров", "Рябинин", "Пушкин", "Чёрный", "Зилов"};
    private final static Random random = new Random();

    private static String generateName() {
        return FIRSTNAMES[random.nextInt(FIRSTNAMES.length)] + " " + LASTNAMES[random.nextInt(LASTNAMES.length)];
    }

    @Autowired
    private OrderRepository orderRepository;

    public void delivery() {
        Queue<Order> orders = new LinkedList<>(orderRepository.findAll().stream().filter(order -> !order.isReady()).toList());

        orders.forEach(order -> {
            if (Objects.nonNull(order.getCourier())) {
                order.setReady(true);
                orderRepository.save(order);
            }
        });

        List<Courier> couriers = courierRepository.findAll();
        orders = new LinkedList<>(orders.stream().filter(order -> !order.isReady()).toList());
        for (int i = 0; i < 7; i++) {
            Queue<Order> finalOrders = orders;
            couriers.forEach(courier -> {
                if (finalOrders.isEmpty()){
                    return;
                }

                Order order = finalOrders.poll();
                order.setCourier(courier);
                orderRepository.save(order);
            });
        }

        orderRepository.saveAll(orders);
    }
}
