package com.example.redis.config;

import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient() {
        try {
            Config config = Config.fromYAML(ResourceUtils.getFile("classpath:redisson.yml"));
            return Redisson.create(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
