package com.example.webflux.service;


import com.example.webflux.dto.Dish;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class KitchenService {
    private List<Dish> menu = Arrays.asList(
            Dish.builder().description("Sesame chicken").build(),
            Dish.builder().description("Lo mein noodles, plain").build(),
            Dish.builder().description("Sweet & sour beef").build()
    );
    private Random picker = new Random();

    /**
     * 요리 스트림 생성
     *
     * @return
     */
    public Flux<Dish> getDishes() {
        return Flux.<Dish>generate(sink -> sink.next(randomDish()))
                .delayElements(Duration.ofMillis(250));
    }

    /**
     * 요리 무작위 선택
     *
     * @return
     */
    private Dish randomDish() {
        return menu.get(picker.nextInt(menu.size()));
    }

    public Mono<Dish> getADish() {
        return Mono.create(dishMonoSink -> dishMonoSink.success(randomDish()));
    }
}
