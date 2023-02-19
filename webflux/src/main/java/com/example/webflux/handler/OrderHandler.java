package com.example.webflux.handler;

import com.example.webflux.dto.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

    public Mono<ServerResponse> getOrders(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.just(new Order(1L, 100.0),
                                new Order(2L, 200.0),
                                new Order(3L, 300.0)),
                        Order.class);
    }

    public Mono<ServerResponse> getOrder(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Mono.just(new Order(1L, 100.0)),
                        Order.class);
    }
}
