package com.eazybytes.hellokafka.model;

public record WindowedOrderCount(
        long count
) {
    public WindowedOrderCount increment() {
        return new WindowedOrderCount(this.count + 1);
    }
}
