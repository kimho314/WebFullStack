package com.example.webflux.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document
@Builder
public record Cart(@Id String id,
                   List<CartItem> cartItems) {
    public Cart(String id) {
        this(id, Collections.EMPTY_LIST);
    }
}
