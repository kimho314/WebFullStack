package com.example.troubleshootingjava.ch9_1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer extends Thread {
    public Consumer(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            synchronized (Main.list) {
                if (Main.list.size() > 0) {
                    int x = Main.list.get(0);
                    Main.list.remove(0);
                    log.info("Consumer " + Thread.currentThread().getName() + " removed value " + x);
                }
            }
        }
    }
}
