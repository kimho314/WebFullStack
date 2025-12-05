package com.luna.jspecifydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JspecifyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JspecifyDemoApplication.class, args);
        TokenExtractor extractor = new SimpleTokenExtractor();
        String token = extractor.extractToken("...");
        // Uncomment the if statement to fix the nullability issue
        // if (token != null) {
        System.out.println("The token has a length of " + token.length());
        // }
    }

}
