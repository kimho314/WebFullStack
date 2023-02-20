package com.example.webflux.repository.mongodb;

import com.example.webflux.entity.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItemRepository extends ReactiveMongoRepository<Item, String> {
}
