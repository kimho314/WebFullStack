package com.example.authserver.controller;

import com.example.authserver.dto.GetShortestDistanceDto;
import com.example.authserver.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestController
public class RouteController {
    private final RouteService routeService;


    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/shortest-distance")
    public GetShortestDistanceDto getShortestDistance(){
        UUID uuid = UUID.randomUUID();
        AtomicReference<Double> distance = new AtomicReference<>(0.0);
        CompletableFuture<Double> futureDistance = routeService.getRandomShortestDistance();
        CompletableFuture.anyOf(futureDistance).thenAccept(o -> distance.set((Double) o));
        return new GetShortestDistanceDto(uuid.toString(), distance.get());
    }

    @GetMapping("/async-shortest-distance")
    public CompletableFuture<GetShortestDistanceDto> getAsyncShortestDistance(){
        return  routeService.getRandomShortestDistance()
                .thenApply(o -> new GetShortestDistanceDto(UUID.randomUUID().toString(), o));
    }
}
