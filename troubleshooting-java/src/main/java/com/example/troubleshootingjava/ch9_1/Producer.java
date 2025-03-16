package com.example.troubleshootingjava.ch9_1;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer extends Thread{
    public Producer(String name) {
        super(name);
    }

    @Override
    public void run() {
        Random r = new Random();
        for (int i = 0; i < 1_000_000; i++) {
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
