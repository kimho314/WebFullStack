package com.example.troubleshootingjava.ch7_2.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Purchase {
    private int id;
    private int product;
    private BigDecimal price;

    public Purchase(int id, int product, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }
}
