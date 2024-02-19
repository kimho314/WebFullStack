package com.example.bookmanager.controller;

import com.example.bookmanager.service.OrderServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderServiceV2;

    @GetMapping("/v2/request/{itemId}")
    public String request(@PathVariable(name = "itemId") String itemId) {
        orderServiceV2.orderItem(itemId);
        return "ok";
    }
}
