package com.example.bookmanager.service;

import com.example.bookmanager.repository.OrderRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepositoryV2;

    public void orderItem(String itemId) {
        orderRepositoryV2.save(itemId);
    }
}
