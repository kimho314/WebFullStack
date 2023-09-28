package com.example.bookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.bookmanager")
public class BookmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmanagerApplication.class, args);
    }

}
