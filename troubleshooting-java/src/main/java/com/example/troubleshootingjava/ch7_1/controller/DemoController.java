package com.example.troubleshootingjava.ch7_1.controller;

import com.example.troubleshootingjava.ch7_1.proxy.DemoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final DemoProxy demoProxy;

    public DemoController(DemoProxy demoProxy) {
        this.demoProxy = demoProxy;
    }

    @GetMapping("/demo")
    public void demo(){
        demoProxy.delay(5);
    }
}
