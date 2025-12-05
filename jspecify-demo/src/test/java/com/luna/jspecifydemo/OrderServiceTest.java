package com.luna.jspecifydemo;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void test1(){
        Order order = orderService.getOrderById(0L);
        Assertions.assertThat(order).isNull();
    }

    @Test
    void test2(){
        Order order = orderService.getOrderById(null);
        Assertions.assertThat(order).isNull();
    }

    @Test
    void test3(){
        Order order = orderService.getOrderById(1L);
        Assertions.assertThat(order).isNotNull();
    }
}
