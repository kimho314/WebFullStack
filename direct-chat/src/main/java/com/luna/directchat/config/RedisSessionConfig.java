package com.luna.directchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson.SecurityJacksonModules;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@EnableRedisHttpSession(
        redisNamespace = "message:user_session",
        maxInactiveIntervalInSeconds = 300,
        flushMode = FlushMode.IMMEDIATE)
public class RedisSessionConfig {
    @Bean
    public RedisSerializer<Object> springSessionDefaultREdisSerializer() {
        JsonMapper mapper = JsonMapper.builder()
                .addModules(SecurityJacksonModules.getModules(getClass().getClassLoader()))
                .build();
        return new GenericJacksonJsonRedisSerializer(mapper);
    }
}
