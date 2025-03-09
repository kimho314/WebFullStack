package com.example.troubleshootingjava.ch6_2;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer extends Thread {
    @Override
    public void run() {
        Random r = new Random();
        while (true) {
            synchronized (Main.list) {
                if (Main.list.size() < 100) {
                    int x = r.nextInt();
                    Main.list.add(x);
                    log.info("Producer " + Thread.currentThread().getName() + " added value " + x);
                }
            }
        }
    }
}
