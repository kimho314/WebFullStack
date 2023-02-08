package com.example.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class FLuxTest {
    @Test
    void simpleFluxTest() {
        Flux<String> stringFlux = Flux.just("John", "Raj", "Sachin");
//        stringFlux = stringFlux.concatWithValues("Martin");

        Flux<String> stringFlux2 = Flux.just("Peter", "Martin");
        stringFlux = stringFlux.concatWith(stringFlux2);
        stringFlux.subscribe(log::info);

        stringFlux = Flux.just("John", "Faj", "Sachin")
                .concatWith(Flux.error(new RuntimeException()))
                .concatWith(Flux.just("Peter"))
                .log();

        stringFlux.subscribe(log::info, e -> log.error(e.toString()));
    }

    @Test
    void emptyFLuxTest() {
        Flux<?> emptyFlux = Flux.empty().log();
        emptyFlux.subscribe(o -> log.info(o.toString()));
    }
}
