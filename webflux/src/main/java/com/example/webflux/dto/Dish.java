package com.example.webflux.dto;

import lombok.Builder;

@Builder
public record Dish(String description, boolean delivered) {

    public Dish(String description) {
        this(description, Boolean.FALSE);
    }

    public static Dish deliver(Dish dish) {
        return new Dish(dish.description, Boolean.TRUE);
    }
}
