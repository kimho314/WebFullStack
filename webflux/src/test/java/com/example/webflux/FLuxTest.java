package com.example.webflux;

import com.example.webflux.dto.Order;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
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

    @Test
    void fluxRangeTest() {
        Flux<Integer> integerFlux = Flux.range(5, 10).log();
        integerFlux.subscribe(it -> log.info(it.toString()));
        Flux<Integer> integerFlux2 = Flux.range(5, 0).log();
        integerFlux2.subscribe(it -> log.info(it.toString()));
        Flux<Integer> integerFlux3 = Flux.range(5, 1).log();
        integerFlux3.subscribe(it -> log.info(it.toString()));
    }

    @Test
    void fluxFilterTest() {
        Flux<String> stringFlux = Flux.just("John", "Raj", "Sachin")
                .filter(it -> it.length() > 3)
                .log();
        stringFlux.subscribe(log::info);
    }

    @Test
    void fluxMapTest() {
        Flux<Order> orderFlux = Flux.just(
                new Order(1L, 100.0),
                new Order(2L, 200.0),
                new Order(3L, 300.0)
        );

        orderFlux.filter(it -> it.getAmount() >= 200.0)
                .map(Order::getId)
                .log()
                .subscribe(it -> log.info(it.toString()));
    }

    @Test
    void fluxFlatMapTest() {
        Flux<Long> longFlux = Flux.just(1L, 2L, 3L);
        // one input -> multiple outputs
        Flux<Order> orderFlux = longFlux.flatMap(this::getOrders);
        orderFlux.subscribe(it -> log.info(it.getAmount().toString()));
    }

    Flux<Order> getOrders(Long orderId) {
        return Flux.just(
                new Order(1L, 100.0),
                new Order(2L, 200.0),
                new Order(3L, 300.0)
        );
    }

    @Test
    void backPressureTest() {
        Flux<Integer> integerFlux = Flux.range(1, 10).log();
//        integerFlux.subscribe(it -> log.info(it.toString()),
//                Throwable::printStackTrace,
//                () -> log.info("# complete")
//        );

        integerFlux.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                subscription.request(5);
            }

            @Override
            protected void hookOnNext(Integer value) {
                log.info(value.toString());
                if (value == 3) {
                    cancel();
                }
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                log.error(Arrays.toString(throwable.getStackTrace()));
            }

            @Override
            protected void hookOnComplete() {
                log.info("# hookOnComplete");
            }
        });
    }
}
