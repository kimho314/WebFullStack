package com.example.troubleshootingjava.ch7_1.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "httpBin", url = "${httpBinUrl}")
public interface DemoProxy {
    @PostMapping("/delay/{n}")
    void delay(@PathVariable int n);
}
