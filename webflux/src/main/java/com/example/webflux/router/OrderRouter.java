package com.example.webflux.router;

import com.example.webflux.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class OrderRouter {
    private final OrderHandler orderHandler;

    public OrderRouter(OrderHandler orderHandler) {
        this.orderHandler = orderHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> orderRouters() {
        return RouterFunctions
                .route(RequestPredicates.GET("/orders"),
                        orderHandler::getOrders)
                .andRoute(RequestPredicates.GET("/order"),
                        orderHandler::getOrder);
    }
}
