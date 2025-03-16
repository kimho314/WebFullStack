package com.example.troubleshootingjava.ch7_2.service;

import com.example.troubleshootingjava.ch7_2.model.Product;
import com.example.troubleshootingjava.ch7_2.model.Purchase;
import com.example.troubleshootingjava.ch7_2.repository.ProductRepositoryV2;
import com.example.troubleshootingjava.ch7_2.repository.PurchaseRepositoryV2;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PurchaseServiceV2 {
    private final ProductRepositoryV2 productRepositoryV2;
    private final PurchaseRepositoryV2 purchaseRepositoryV2;

    public PurchaseServiceV2(ProductRepositoryV2 productRepositoryV2, PurchaseRepositoryV2 purchaseRepositoryV2) {
        this.productRepositoryV2 = productRepositoryV2;
        this.purchaseRepositoryV2 = purchaseRepositoryV2;
    }

    public Set<String> getProductNamesForPurchases(){
        try{
            Set<String> productNames = new HashSet<>();
            List<Purchase> purchases = purchaseRepositoryV2.findAll();
            for(Purchase p : purchases){
                Product product = productRepositoryV2.findProduct(p.getProduct());
                productNames.add(product.getName());
            }
            return productNames;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return Set.of();
    }
}
