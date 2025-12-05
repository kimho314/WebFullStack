package com.luna.jspecifydemo;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
class SimpleOrderService implements OrderService {

    @Override
    public @NonNull Order getOrderById(@NonNull Long id) {
        return id == null || id == 0L ? null : new Order(id, "galaxyS26");
    }
}
