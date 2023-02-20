package com.example.webflux.repository.r2dbc;

import com.example.webflux.entity.Order;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderRepository extends R2dbcRepository<Order, Long> {
}
