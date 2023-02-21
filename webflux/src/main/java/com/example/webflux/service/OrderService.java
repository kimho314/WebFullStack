package com.example.webflux.service;

import com.example.webflux.entity.r2dbc.EcommerceOrder;
import com.example.webflux.repository.r2dbc.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Flux<EcommerceOrder> getOrders() {
        return orderRepository.findAll();
    }
}
