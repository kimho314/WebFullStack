package com.example.troubleshootingjava.ch7_1.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.troubleshootingjava.ch7_1.proxy")
public class AppConfig {

}
