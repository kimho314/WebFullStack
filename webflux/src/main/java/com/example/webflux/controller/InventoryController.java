package com.example.webflux.controller;

import com.example.webflux.entity.Item;
import com.example.webflux.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/search")
    public Flux<Item> search(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String description,
                             @RequestParam boolean useAnd) {
        return inventoryService.searchByExample(name, description, useAnd);
    }
}
