package com.luna.springapplicationevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApplicationEventApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringApplicationEventApplication.class);
        springApplication.addListeners(
                new StartingListener(),
                new EnvPreparedListener(),
                new ApplicationPreparedListener(),
                new ApplicationContextInitializedListener()
        );
        springApplication.run(args);
    }

}
