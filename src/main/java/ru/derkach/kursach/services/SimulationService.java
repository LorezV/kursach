package ru.derkach.kursach.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Scope("singleton")
public class SimulationService {
    private Timer timer;
    private int steps;

    @Autowired
    private CustomerService customerService;

    public void start(int step, int duration) {
        steps = 0;
        if (!isRunning()) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    simulation();
                    customerService.deleteAll();


                    steps++;
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

    public void simulation() {
    }
}
