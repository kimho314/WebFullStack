package com.luna.oracleautonomousedatabasedemo.domain.entity;

import org.springframework.data.annotation.Id;

public record Employee(
    @Id Long id,
    String name,
    Long salary,
    Long commission
) {

}
