package com.example.troubleshootingjava.ch9_1;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Thread.sleep(10_000);

            new Producer("_Producer").start();
            new Consumer("_Consumer").start();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
