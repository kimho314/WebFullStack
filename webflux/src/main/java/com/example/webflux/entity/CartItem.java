package com.example.webflux.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public record CartItem(Item item,
                       Integer quantity) {

    @Builder
    public CartItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = Objects.isNull(quantity) || quantity == 0 ? 1 : quantity;
    }

    public CartItem increment() {
        return CartItem.builder()
                .item(this.item)
                .quantity(this.quantity + 1)
                .build();
    }
}
