package com.example.webflux.entity;

import lombok.Builder;

@Builder
public record CartItem(Item item,
                       int quantity) {
    public CartItem(Item item) {
        this(item, 1);
    }
}
