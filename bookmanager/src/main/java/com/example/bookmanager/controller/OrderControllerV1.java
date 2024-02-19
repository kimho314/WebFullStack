package com.example.bookmanager.controller;

import com.example.bookmanager.service.OrderServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderServiceV1;

    @GetMapping("/v1/request/{itemId}")
    public String request(@PathVariable(name = "itemId") String itemId) {
        orderServiceV1.orderItem(itemId);
        return "ok";
    }
}
