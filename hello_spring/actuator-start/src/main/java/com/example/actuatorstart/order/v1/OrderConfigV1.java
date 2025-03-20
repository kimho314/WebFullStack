package com.example.actuatorstart.order.v1;

import com.example.actuatorstart.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV1 {

    @Bean
    OrderService orderService(MeterRegistry meterRegistry) {
        return new OrderServiceV1(meterRegistry);
    }
}
