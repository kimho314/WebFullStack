package com.example.hello_jpa.jpa_shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private Long id;

//    @Column(name = "order_id")
//    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

//    @Column(name = "item_id")
//    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    private Integer orderPrice;
    private Integer count;

    public OrderItem(Order order, Integer orderPrice, Integer count) {
        this(null, order, orderPrice, count);
    }

    public OrderItem(Long id, Order order, Integer orderPrice, Integer count) {
        this.id = id;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
