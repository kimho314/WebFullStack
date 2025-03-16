package com.example.troubleshootingjava;

import com.example.troubleshootingjava.ch7_2.service.PurchaseServiceV2;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PurchaseControllerTest {
    @Autowired
    PurchaseServiceV2 purchaseServiceV2;
    @Test
    @Transactional
    public void findPurchasedProductNamesV2Test() {
        IntStream.range(0, 1000).forEach((var i) -> {
            Thread thread = new Thread(() -> purchaseServiceV2.getProductNamesForPurchases());
            thread.start();
            System.out.println(i + " " + thread.getName());
        });
    }
}
