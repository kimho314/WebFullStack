package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void asyncHello(int i) {
        log.info("async i = " + i);
    }


    @Async
    public void asyncHello2(int i) {
        throw new RuntimeException("error occurred in asyncHello2");
    }
}
