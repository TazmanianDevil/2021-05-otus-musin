package ru.otus.homework.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckActuator implements HealthIndicator {

    @Override
    public Health getHealth(boolean includeDetails) {
        return Health.up().build();
    }

    @Override
    public Health health() {
        return Health.up().build();
    }
}
