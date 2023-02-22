package com.example.webflux.handler;

import com.example.webflux.entity.r2dbc.EcommerceOrder;
import com.example.webflux.repository.r2dbc.OrderRepository;
import com.example.webflux.request.CreateOrderRequest;
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
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderHandler(
            OrderService orderService,
            OrderRepository orderRepository
    ) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
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
        //Long id = Long.valueOf(serverRequest.pathVariable("id"));
        Long id = Long.valueOf(serverRequest.queryParam("id").orElseThrow());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderService.getOrder(id)
                                .map(order -> OrderResponse.builder()
                                        .id(order.getId())
                                        .amount(order.getAmount())
                                        .placedDate(order.getPlacedDate())
                                        .build()),
                        OrderResponse.class);
    }

    public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
        Mono<CreateOrderRequest> createOrderRequestMono = serverRequest.bodyToMono(CreateOrderRequest.class);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        createOrderRequestMono.map(
                                        order -> EcommerceOrder.builder()
                                                .amount(order.amount())
                                                .placedDate(order.placedDate())
                                                .build())
                                .flatMap(orderService::create)
                                .map(order -> OrderResponse.builder()
                                        .id(order.getId())
                                        .amount(order.getAmount())
                                        .placedDate(order.getPlacedDate())
                                        .build()),
                        OrderResponse.class
                );
    }
}
