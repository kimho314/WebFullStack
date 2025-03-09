package com.example.troubleshootingjava.ch6_2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer extends Thread{
    @Override
    public void run() {
        while (true) {
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
