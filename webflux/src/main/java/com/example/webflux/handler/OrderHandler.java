package com.example.webflux.handler;

import com.example.webflux.dto.Order;
import com.example.webflux.response.OrderResponse;
import com.example.webflux.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OrderHandler {
    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<ServerResponse> getOrders(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(orderService.getOrders().map(order -> OrderResponse.builder()
                                .id(order.getId())
                                .amount(order.getAmount())
                                .placedDate(order.getPlacedDate())
                                .build()),
                        OrderResponse.class);
    }

    public Mono<ServerResponse> getOrder(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Mono.just(new Order(1L, 100.0)),
                        Order.class);
    }
}
