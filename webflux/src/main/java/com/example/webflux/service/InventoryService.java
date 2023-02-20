package com.example.webflux.service;

import com.example.webflux.entity.Cart;
import com.example.webflux.entity.Item;
import com.example.webflux.repository.mongodb.CartRepository;
import com.example.webflux.repository.mongodb.ItemByExampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class InventoryService {

    private final ItemByExampleRepository itemByExampleRepository;
    private final CartRepository cartRepository;

    public InventoryService(ItemByExampleRepository itemByExampleRepository, CartRepository cartRepository) {
        this.itemByExampleRepository = itemByExampleRepository;
        this.cartRepository = cartRepository;
    }

    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        var item = Item.builder().name(name).description(description).price(0.0).build();

        var matcher = (useAnd
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny())
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");
        var probe = Example.of(item, matcher);

        return itemByExampleRepository.findAll(probe);
    }

    public Flux<Item> getInventory() {
        var inventories = itemByExampleRepository.findAll();
        return inventories;
    }

    public Mono<Cart> getCart(String id) {
        var cart = cartRepository.findById(id);
        return cart;
    }
}
