package com.example.async.controller;


import com.example.async.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {
    private final TestService testService;

    public HomeController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/async")
    public void async() {
        testService.callAsyncFunc();
    }

    @GetMapping("/async2")
    public void async2() {
        testService.callAsyncFunc2();
    }
}
