package com.example.springbootexample.spring;

import org.springframework.context.annotation.Bean;

//@Configuration
public class HelloConfig {

    @Bean
    public HelloController helloController() {
        return new HelloController();
    }
}
