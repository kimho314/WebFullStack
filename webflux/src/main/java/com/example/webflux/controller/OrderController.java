package com.example.webflux.controller;

import com.example.webflux.dto.Order;
import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

    @GetMapping(value = "/orders", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Order> getOrders() {
        return Flux.just(new Order(1L, 100.0),
                        new Order(2L, 200.0),
                        new Order(3L, 300.0))
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/order")
    public Mono<Order> getOrder() {
        return Mono.just(new Order(1L, 100.0)).log();
    }
}
