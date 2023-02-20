package com.example.webflux.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = {"com.example.webflux.repository.r2dbc"})
public class R2dbcConfiguration {
}
