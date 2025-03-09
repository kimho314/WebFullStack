package com.example.troubleshootingjava.ch6_1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer extends Thread{
    @Override
    public void run() {
        while (true) {
            if (!Main.list.isEmpty()) {
                int x = Main.list.getFirst();
                Main.list.removeFirst();
                log.info("Consumer " + Thread.currentThread().getName() + " removed value " + x);
            }
        }
    }
}
