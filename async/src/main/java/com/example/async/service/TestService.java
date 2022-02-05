package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
    private final AsyncService asyncService;

    public TestService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public void callAsyncFunc() {
        for (int i = 0; i < 10; i++) {
            asyncService.asyncHello(i);
        }
    }

    public void callAsyncFunc2() {
        for (int i = 0; i < 10; i++) {
            asyncService.asyncHello2(i);
        }
    }
}
