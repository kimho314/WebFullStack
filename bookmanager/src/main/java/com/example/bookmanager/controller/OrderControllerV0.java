package com.example.bookmanager.controller;

import com.example.bookmanager.service.OrderServiceV0;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {
    private final OrderServiceV0 orderServiceV0;

    @GetMapping("/v0/request/{itemId}")
    public String request(@PathVariable(name = "itemId") String itemId) {
        orderServiceV0.orderItem(itemId);
        return "ok";
    }
}
