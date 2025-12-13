package com.luna.springapplicationevent;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * SpringApplication.run() 직후
 * ApplicationContext 생성 전
 */
public class StartingListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("ApplicationStartingEvent 발생");
    }
}
