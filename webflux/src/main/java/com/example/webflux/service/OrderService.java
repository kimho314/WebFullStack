package com.example.webflux.service;

import com.example.webflux.entity.r2dbc.EcommerceOrder;
import com.example.webflux.repository.r2dbc.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Flux<EcommerceOrder> getOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mono<EcommerceOrder> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Mono<EcommerceOrder> create(EcommerceOrder ecommerceOrder) {
        return orderRepository.save(ecommerceOrder);
    }
}
