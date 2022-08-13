package com.example.webflux.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
public record Item(@Id String id,
                   String name,
                   String description,
                   double price) {
}
