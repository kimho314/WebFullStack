package com.example.troubleshootingjava.ch7_2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
    private int id;
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
