package com.eazybytes.hellokafka.model;

import java.math.BigDecimal;
import java.util.Objects;

public record WindowedSalesData(
        BigDecimal totalSales,
        long orderCount
) {
    public WindowedSalesData(BigDecimal totalSales, long orderCount) {
        if (Objects.isNull(totalSales)) {
            this.totalSales = BigDecimal.ZERO;
        }
        else {
            this.totalSales = totalSales;
        }
        this.orderCount = orderCount;
    }

    public WindowedSalesData add(BigDecimal orderValue) {
        return new WindowedSalesData(this.totalSales.add(orderValue), this.orderCount + 1);
    }
}
