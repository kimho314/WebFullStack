package com.example.webflux.request;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CreateOrderRequest(
        Double amount,
        LocalDateTime placedDate
) {
}
