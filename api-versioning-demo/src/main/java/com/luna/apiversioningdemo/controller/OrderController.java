package com.luna.apiversioningdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping(path = "/orders/{id}", version = "1", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrderV1(@PathVariable(value = "id") Long id) {
        return "order: " + id;
    }

    @GetMapping(path = "/{version}/orders/{id}", version = "2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrderV2(@PathVariable(value = "id") Long id) {
        return "order: " + id;
    }
}
