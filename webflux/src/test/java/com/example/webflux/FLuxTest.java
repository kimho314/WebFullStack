package com.example.webflux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
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

    @Test
    void listToFluxTest() {
        List<String> list = Arrays.asList("John", "Raj", "Peter");
        Flux<String> stringFlux = Flux.fromIterable(list).log();
        stringFlux.subscribe(log::info);
    }

    @Test
    void streamToFluxTest() {
        List<String> list = Arrays.asList("John", "Raj", "Peter");
        Stream<String> stream = list.stream();
        Flux<String> stringFlux = Flux.fromStream(stream).log();
        stringFlux.subscribe(log::info);
    }

    @Test
    void arrayToFluxTest() {
        String[] array = {"John", "Raj", "Peter"};
        Flux<String> stringFlux = Flux.fromArray(array).log();
        stringFlux.subscribe(log::info);
    }
}
