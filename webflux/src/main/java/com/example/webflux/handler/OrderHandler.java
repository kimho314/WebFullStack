package com.example.webflux.handler;

import com.example.webflux.entity.r2dbc.EcommerceOrder;
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
        //Long id = Long.valueOf(serverRequest.pathVariable("id"));
        Long id = Long.valueOf(serverRequest.queryParam("id").orElseThrow());
        String firstHeader = serverRequest.headers()
                .firstHeader("first-header"); // get a first header named "first-header"

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header("response-header", firstHeader)
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

    public Mono<ServerResponse> updateOrder(ServerRequest serverRequest) {
        Mono<CreateOrderRequest> createOrderRequestMono = serverRequest.bodyToMono(CreateOrderRequest.class);
        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        orderService.getOrder(id)
                                .flatMap(order -> createOrderRequestMono.map(request ->
                                        EcommerceOrder.builder()
                                                .id(order.getId())
                                                .amount(request.amount())
                                                .placedDate(request.placedDate())
                                                .build()))
                                .flatMap(orderService::create)
                                .map(order -> OrderResponse.builder()
                                        .id(order.getId())
                                        .amount(order.getAmount())
                                        .placedDate(order.getPlacedDate())
                                        .build()),
                        OrderResponse.class
                );
    }

    public Mono<ServerResponse> deleteOrder(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        orderService.getOrder(id)
                                .switchIfEmpty(Mono.empty())
                                .flatMap(order -> orderService.deleteById(id)
                                        .map(it -> OrderResponse.builder()
                                                .id(order.getId())
                                                .amount(order.getAmount())
                                                .placedDate(order.getPlacedDate())
                                                .build())),
                        OrderResponse.class
                );
    }
}
