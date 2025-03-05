package com.example.externalread;

import com.example.externalread.config.MyDatasourceValueConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import(MyDatasourceEnvConfig.class)
@Import(MyDatasourceValueConfig.class)
@SpringBootApplication(scanBasePackages = "com.example.externalread.datasource")
public class ExternalReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }

}
