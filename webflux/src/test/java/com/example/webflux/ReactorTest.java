package com.example.webflux;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@SpringBootTest(classes = WebfluxApplication.class)
public class ReactorTest {

    @Test
    void fluxTest() {
        List<Integer> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(elements::add);

        assertThat(elements).containsExactly(1, 2, 3, 4);
    }

    @Test
    void fluxDealtWithBackpressureTest() {
        List<Integer> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Test
    void fluxInStreamTest() {
        List<Integer> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2) // map will be applied when onNext() is called
                .subscribe(elements::add);
        log.info("# elements : {}", elements);
    }

    @Test
    void fluxCombiningAnotherStreamTest() {
        List<String> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .zipWith(Flux.range(0, Integer.MAX_VALUE).log(),
                        (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
                .subscribe(elements::add);

        assertThat(elements).containsExactly(
                "First Flux: 2, Second Flux: 0",
                "First Flux: 4, Second Flux: 1",
                "First Flux: 6, Second Flux: 2",
                "First Flux: 8, Second Flux: 3");
    }

    @Test
    void createConnectableFluxTest() {
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    IntStream.range(0, 10).forEach(value -> fluxSink.next(System.currentTimeMillis() + " : " + value));
                })
                .publish();

        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);

        publish.connect();
    }

    @Test
    void fluxThrottlingTest() {
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    IntStream.range(0, 100000).forEach(value -> fluxSink.next(System.currentTimeMillis() + " : " + value));
                })
                .sample(ofSeconds(2))
                .publish();

        publish.subscribe(System.out::println);

        publish.connect();
    }

    @Test
    void fluxConcurrencyTest() {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .subscribeOn(Schedulers.parallel())
                .subscribe(elements::add);
    }
}
