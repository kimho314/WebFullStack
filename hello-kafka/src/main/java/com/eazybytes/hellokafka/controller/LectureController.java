package com.eazybytes.hellokafka.controller;

import com.eazybytes.hellokafka.basic.OrderEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lecture")
public class LectureController {
    private final OrderEventPublisher orderEventPublisher;

    public LectureController(OrderEventPublisher orderEventPublisher) {
        this.orderEventPublisher = orderEventPublisher;
    }

    
}
