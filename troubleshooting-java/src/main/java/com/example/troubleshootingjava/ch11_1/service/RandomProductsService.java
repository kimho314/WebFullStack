package com.example.troubleshootingjava.ch11_1.service;

import com.example.troubleshootingjava.ch11_1.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class RandomProductsService {
    public List<Product> getRandomProductsList(int n) {
        List<Product> result = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int randomValue = r.nextInt(1000);

            Product p = new Product();
            p.setPrice(randomValue);

            result.add(p);
        }

        return result;
    }
}
