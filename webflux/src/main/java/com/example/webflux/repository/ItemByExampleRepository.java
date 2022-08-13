package com.example.webflux.repository;

import com.example.webflux.entity.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItemByExampleRepository extends ReactiveMongoRepository<Item, String> {
}
