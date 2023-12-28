package ru.derkach.kursach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.derkach.kursach.models.Customer;
import ru.derkach.kursach.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CustomerService {
    private final static String[] FIRSTNAMES = {"Дима", "Вадим", "Ярослав", "Миша", "Александр", "Евгений"};
    private final static String[] LASTNAMES = {"Садыкин", "Лексечей", "Киров", "Мифазин", "Доктор", "Ларин"};

    private final static String[] ADDRESSES = {"Петровская", "Галинская", "Муровская", "Табличная", "Чкалова"};

    private final static Random random = new Random();

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll(Sort.by("id"));
    }

    public void generate(int count) {
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Customer customer = new Customer();
            customer.setName(generateName());
            customer.setAddress(generateAddress());
            customer.setPhone(generatePhone());
            customer.setDiscountCard(generateDiscountCard());
            customers.add(customer);
        }

        customerRepository.saveAll(customers);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    private static String generateAddress() {
        return ADDRESSES[random.nextInt(ADDRESSES.length)] + " " + String.valueOf(random.nextInt(120));
    }

    private static String generateName() {
        return FIRSTNAMES[random.nextInt(FIRSTNAMES.length)] + " " + LASTNAMES[random.nextInt(LASTNAMES.length)];
    }

    private static String generatePhone() {
        return "+7" + String.valueOf(random.nextInt(10000, 99999)) + String.valueOf(random.nextInt(10000, 99999));
    }

    private static String generateDiscountCard() {
        return String.valueOf(random.nextInt(10000, 99999));
    }
}
