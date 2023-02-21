package com.example.webflux.entity.r2dbc;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("ecommerce_order")
public class EcommerceOrder {
    @Id
    private Long id;
    private Double amount;
    private LocalDateTime placedDate;
}
