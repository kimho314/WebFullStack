package com.example.webflux.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EntityScan(value = {"com.example.webflux.entity.r2dbc"})
@EnableR2dbcRepositories(basePackages = {"com.example.webflux.repository.r2dbc"})
public class R2dbcConfiguration {
}
