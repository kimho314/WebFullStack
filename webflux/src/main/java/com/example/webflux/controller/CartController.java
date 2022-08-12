package com.example.webflux.controller;

import com.example.webflux.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/{id}")
    public Mono<String> addToCart(@PathVariable String id) {
        return cartService.addToCart("My Cart", id)
                .thenReturn("redirect:/");
    }
}
