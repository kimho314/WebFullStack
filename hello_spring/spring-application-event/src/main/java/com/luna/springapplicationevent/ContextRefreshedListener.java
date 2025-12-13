package com.luna.springapplicationevent;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext 초기화 완료
 * 모든 싱글톤 Bean 생성 완료
 * 웹 서버(Tomcat) 준비 여부는 보장되지 않음
 * Context가 refresh될 때마다 발생 -> 여러 번 발생 가능
 */
@Component
public class ContextRefreshedListener {
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ContextRefreshedEvent 발생");
    }
}
