package com.example.hello_jpa.jpa_shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "member_id")
    private Long memberId;
    private LocalDateTime orderDateTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
