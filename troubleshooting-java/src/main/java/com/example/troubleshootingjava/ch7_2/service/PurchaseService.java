package com.example.troubleshootingjava.ch7_2.service;

import com.example.troubleshootingjava.ch7_2.model.Product;
import com.example.troubleshootingjava.ch7_2.model.Purchase;
import com.example.troubleshootingjava.ch7_2.repository.ProductRepository;
import com.example.troubleshootingjava.ch7_2.repository.PurchaseRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(ProductRepository productRepository,
        PurchaseRepository purchaseRepository) {
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Set<String> getProductNamesForPurchases() {
        Set<String> productNames = new HashSet<>();
        List<Purchase> purchases = purchaseRepository.findAll();
        for (Purchase p : purchases) { // for 문들 돌면서 product 조회 쿼리를 날린다.
            Product product = productRepository.findProduct(p.getProduct());
            productNames.add(product.getName());
        }
        return productNames;
    }
}
