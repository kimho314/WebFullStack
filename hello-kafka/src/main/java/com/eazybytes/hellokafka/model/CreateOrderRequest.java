package com.eazybytes.hellokafka.model;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String customerId,
        Integer quantity,
        BigDecimal price
) {
}
