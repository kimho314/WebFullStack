package com.luna.springapplicationevent;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;

/**
 * ApplicationContext 생성됨
 * Bean 등록 전
 */
public class ApplicationContextInitializedListener implements ApplicationListener<ApplicationContextInitializedEvent> {
    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        System.out.println("ApplicationContextInitializedEvent 발생");
    }
}
