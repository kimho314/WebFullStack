package com.example.webflux;

import com.example.webflux.dto.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SchedulerTest {
    private static Flux<Order> orderFlux;

    @BeforeEach
    void init() {
        log.info("started with - " + Thread.currentThread().getName());
        orderFlux = Flux.just(
                new Order(1L, 100.0),
                new Order(2L, 200.0),
                new Order(3L, 300.0)
        );
    }

    @Test
    void immediateSchedulerTest() {
        Flux<Long> longFlux = orderFlux
                .publishOn(Schedulers.immediate())
                .map(order -> {
                    log.info("map with - " + Thread.currentThread().getName());
                    return order.getId();
                });
        longFlux.subscribe(orderId -> log.info("sub with - " + Thread.currentThread().getName()));
    }

    @Test
    @SneakyThrows
    void parallelSchedulerTest() {
        // Schedulers.parallel() : make new thread to downstream with random thread name
        // Schedulers.newParallel("my-parallel") : make new thread to downstream with name "my-parallel"
        Flux<Long> longFlux = orderFlux
                .publishOn(Schedulers.newParallel("my-parallel"))
                .map(order -> {
                    log.info("map with - " + Thread.currentThread().getName());
                    return order.getId();
                })
                .publishOn(Schedulers.parallel())
                .map(orderId -> {
                    log.info("map with - " + Thread.currentThread().getName());
                    return orderId * 2;
                });
        longFlux.subscribe(orderId -> log.info("sub with - " + Thread.currentThread().getName()));
        Thread.sleep(3000);
    }

    @Test
    @SneakyThrows
    void singleSchedulerTest() {
        // Schedulers.single() : switch to a new thread only once
        Flux<Long> longFlux = orderFlux
                .publishOn(Schedulers.single())
                .map(order -> {
                    log.info("map with - " + Thread.currentThread().getName());
                    return order.getId();
                })
                .publishOn(Schedulers.single())
                .map(orderId -> {
                    log.info("map with - " + Thread.currentThread().getName());
                    return orderId * 2;
                });
        longFlux.subscribe(orderId -> log.info("sub with - " + Thread.currentThread().getName()));
        Thread.sleep(3000);
    }
}
