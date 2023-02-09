package com.example.webflux;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoTest {
    @Test
    void monoTest() {
//        Mono<String> stringMono = Mono.just("John").log();
        Mono<Object> stringMono = Mono.error(new RuntimeException()).log();
        stringMono.subscribe(o -> log.info((String) o));
    }

    @Test
    void emptyMonoTest() {
        Mono<?> emptyMono = Mono.empty().log();
        emptyMono.subscribe(o -> log.info(o.toString()));
        log.info("==========");
        Mono<?> mono = Mono.justOrEmpty(Optional.empty()).log();
        mono.subscribe(o -> log.info(o.toString()));
    }

    @Test
    void optionalMonoTest() {
        Optional<String> optional = Optional.of("John");
        Mono<String> stringMono = Mono.justOrEmpty(optional).log();
        stringMono.subscribe(log::info);
    }
}
