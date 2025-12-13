package com.luna.springapplicationevent;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Context 준비 완료
 * Runner 실행 전
 */
@Component
public class ApplicationStartedListener {
    @EventListener(ApplicationStartedEvent.class)
    public void onStarted(ApplicationStartedEvent event) {
        System.out.println("ApplicationStartedEvent 발생 - " + event.getTimeTaken());
    }
}
