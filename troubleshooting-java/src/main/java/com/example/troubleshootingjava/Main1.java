package com.example.troubleshootingjava;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main1 {
    private record Product(String id){

    }

    private static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        while(true){
            products.add(new Product(UUID.randomUUID().toString()));
        }
    }
}
