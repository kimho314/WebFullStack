package com.example.troubleshootingjava.ch11_1.controller;

import com.example.troubleshootingjava.ch11_1.model.Product;
import com.example.troubleshootingjava.ch11_1.service.RandomProductsService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomProductController {

    private final RandomProductsService randomProductsService;

    public RandomProductController(RandomProductsService randomProductsService) {
        this.randomProductsService = randomProductsService;
    }

    @GetMapping("/products/{n}")
    public List<Product> getRandomProducts(@PathVariable Integer n){
        return randomProductsService.getRandomProductsList(n);
    }
}
