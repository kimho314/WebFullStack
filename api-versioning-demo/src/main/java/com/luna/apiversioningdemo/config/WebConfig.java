package com.luna.apiversioningdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        configurer.useRequestHeader("API-Version"); // api versioning with custom header
        configurer.usePathSegment(0); // api versioning with uri path
    }
}