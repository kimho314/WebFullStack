package com.example.webflux.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.List;

@Builder
public record Cart(@Id String id,
                   List<CartItem> cartItems) {
    public Cart(String id) {
        this(id, Collections.EMPTY_LIST);
    }
}
