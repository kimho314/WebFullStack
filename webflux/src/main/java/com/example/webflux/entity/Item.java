package com.example.webflux.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Item(@Id String id,
                   String name,
                   double price) {
}
