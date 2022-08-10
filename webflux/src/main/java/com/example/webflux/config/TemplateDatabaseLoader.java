package com.example.webflux.config;

import com.example.webflux.entity.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {

    @Bean
    public CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
            mongo.save(Item.builder().name("Alf alarm clock").price(19.99).build());
            mongo.save(Item.builder().name("Smurf TV tray").price(24.99).build());
        };
    }
}
