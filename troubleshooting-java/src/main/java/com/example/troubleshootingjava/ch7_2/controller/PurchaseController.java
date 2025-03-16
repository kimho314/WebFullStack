package com.example.troubleshootingjava.ch7_2.controller;

import com.example.troubleshootingjava.ch7_2.service.PurchaseService;
import com.example.troubleshootingjava.ch7_2.service.PurchaseServiceV2;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final PurchaseServiceV2 purchaseServiceV2;
    public PurchaseController(PurchaseService purchaseService, PurchaseServiceV2 purchaseServiceV2) {
        this.purchaseService = purchaseService;
        this.purchaseServiceV2 = purchaseServiceV2;
    }

    @GetMapping("/products")
    public Set<String> findPurchasedProductNames() {
        return purchaseService.getProductNamesForPurchases();
    }

    @GetMapping("/v2/products")
    public Set<String> findPurchasedProductNamesV2(){
        return purchaseServiceV2.getProductNamesForPurchases();
    }

}
