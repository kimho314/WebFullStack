package com.example.actuatorstart.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {

    void order();

    void cancel();

    AtomicInteger getStock();
}
