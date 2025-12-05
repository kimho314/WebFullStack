package com.luna.jspecifydemo;

import org.jspecify.annotations.NonNull;

public interface OrderService {
    @NonNull Order getOrderById(@NonNull Long id);
}
