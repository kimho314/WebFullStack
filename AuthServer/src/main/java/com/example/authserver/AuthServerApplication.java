package com.example.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(AuthServerApplication.class);
//        springApplication.setLazyInitialization(true);
//        springApplication.run(args);
        SpringApplication.run(AuthServerApplication.class, args);
    }

}
