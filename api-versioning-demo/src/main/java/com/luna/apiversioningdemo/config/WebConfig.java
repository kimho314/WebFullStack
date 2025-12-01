package com.luna.apiversioningdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.addSupportedVersions("1.0", "2.0")
                .useRequestHeader("API-Version")
                .setVersionParser(new MyCustomAPIVersionParser()); // api versioning with custom header
//        configurer.usePathSegment(0); // api versioning with uri path
    }
}