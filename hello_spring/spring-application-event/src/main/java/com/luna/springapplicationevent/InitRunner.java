package com.luna.springapplicationevent;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ApplicationStartedEvent 이후
 * ApplicationReadyEvent 이전
 */
@Component
public class InitRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(args.getOptionNames())
                .append(" ")
                .append(Arrays.toString(args.getSourceArgs()))
                .append(" ")
                .append(args.getNonOptionArgs());
        System.out.println("ApplicationRunner 실행 - " + sb.toString());
    }
}
