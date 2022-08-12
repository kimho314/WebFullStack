package com.example.webflux.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Document
public record Cart(@Id String id,
                   List<CartItem> cartItems) {
    @Builder
    public Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = CollectionUtils.isEmpty(cartItems) ? new ArrayList<>() : cartItems;
    }
}
