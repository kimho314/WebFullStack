package com.example.actuatorstart;

import com.example.actuatorstart.order.v0.OrderConfigV0;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(OrderConfigV0.class)
@SpringBootApplication(scanBasePackages = {"com.example.actuatorstart.log.controller", "com.example.actuatorstart.order.controller"})
public class ActuatorStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorStartApplication.class, args);
    }


    @Bean
    public InMemoryHttpExchangeRepository httpExchangeRepository() {
        return new InMemoryHttpExchangeRepository();
    }
}
