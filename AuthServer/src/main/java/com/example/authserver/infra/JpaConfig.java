package com.example.authserver.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.authserver.domain.**.repository"})
public class JpaConfig {
}
