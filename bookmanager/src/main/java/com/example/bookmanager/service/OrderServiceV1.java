package com.example.bookmanager.service;

import com.example.bookmanager.repository.OrderRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;

    public void orderItem(String itemId) {
        orderRepositoryV1.save(itemId);
    }
}
