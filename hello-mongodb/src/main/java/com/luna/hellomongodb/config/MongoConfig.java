package com.luna.hellomongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.luna.hellomongodb.repository")
public class MongoConfig {
}
