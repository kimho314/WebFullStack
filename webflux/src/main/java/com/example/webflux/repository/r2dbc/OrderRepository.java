package com.example.webflux.repository.r2dbc;

import com.example.webflux.entity.r2dbc.EcommerceOrder;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderRepository extends R2dbcRepository<EcommerceOrder, Long> {
}
