package ru.derkach.kursach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.function.SupplierUtils;
import ru.derkach.kursach.dto.simulation.SimulationStatusDto;
import ru.derkach.kursach.models.OrderProduct;
import ru.derkach.kursach.models.Product;
import ru.derkach.kursach.models.ProductUnit;
import ru.derkach.kursach.models.Supply;
import ru.derkach.kursach.repositories.*;

import java.util.*;

@Service
@Scope("singleton")
public class SimulationService {
    private Timer timer;
    private int steps;
    private int step;
    private int duration;
    private int couriers;

    public SimulationStatusDto getStatus() {
        SimulationStatusDto dto = new SimulationStatusDto();
        dto.setStatus(this.isRunning());
        dto.setDuration(this.duration);
        dto.setSteps(this.steps);
        dto.setCouriers(this.couriers);
        dto.setStep(this.step);

        return dto;
    }

    public void start(int step, int duration, int couriers) {
        this.step = step;
        this.duration = duration + 1;
        this.couriers = couriers;
        this.steps = 1;

        initSimulation();

        if (!isRunning()) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    simulation();

                    steps += step;
                    if (steps >= duration) {
                        stop();
                    }
                }
            };
            timer.schedule(task, 0, step * 1000L);
        }
    }

    public void stop() {
        if (isRunning()) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    public boolean isRunning() {
        return Objects.nonNull(timer);
    }

    @Autowired
    private CourierService courierService;
    @Autowired
    private ProductUnitService productUnitService;
    @Autowired
    private SupplyService supplyService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private CustomerService customerService;
    private static final Random random = new Random();

    public void initSimulation() {
        productUnitService.deleteAll();
        supplyService.deleteAll();
        orderProductRepository.deleteAll();
        orderService.deleteAll();
        customerService.deleteAll();
        courierService.deleteAll();

        courierService.generate(couriers);
        productUnitService.generate(this.getStatus().getDuration());
        customerService.generate(random.nextInt(10, 20));
    }

    public void simulation() {
        supplyService.supply(this.steps, this.duration);
        courierService.delivery();
        orderService.generate();
    }
}
