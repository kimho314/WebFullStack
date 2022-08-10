package com.example.webflux.repository;

import com.example.webflux.entity.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CartRepository extends ReactiveMongoRepository<Cart, String> {
}
