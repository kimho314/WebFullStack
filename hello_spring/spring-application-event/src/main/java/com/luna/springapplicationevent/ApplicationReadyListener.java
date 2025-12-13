package com.luna.springapplicationevent;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 모든 Runner 실행 완료 후
 * 웹 서버 완전 기동
 * 외부 요청 수신 가능 상태
 * 애플리케이션당 1회만 발생
 * 실질적인 서비스 시작 시점
 */
@Component
public class ApplicationReadyListener {
    @EventListener(ApplicationReadyEvent.class)
    public void onReady(ApplicationReadyEvent event) {
        System.out.println("ApplicationReadyEvent 발생 - " + event.getTimeTaken());
    }
}
