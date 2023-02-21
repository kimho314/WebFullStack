package com.example.webflux.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderResponse(
        Long id,
        Double amount,
        LocalDateTime placedDate
) {
}
