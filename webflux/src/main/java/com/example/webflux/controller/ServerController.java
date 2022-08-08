package com.example.webflux.controller;

import com.example.webflux.dto.Dish;
import com.example.webflux.service.KitchenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ServerController {

    private final KitchenService kitchenService;

    public ServerController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> serveDishes() {
        return this.kitchenService.getDishes();
    }

    @GetMapping(value = "/mono-server")
    public Mono<Dish> serveADish() {
        return kitchenService.getADish();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> deliverDishes() {
        return kitchenService.getDishes()
                .map(Dish::deliver);
    }
}
