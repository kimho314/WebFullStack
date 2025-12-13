package com.luna.springapplicationevent;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * BeanDefinition 등록 완료
 * Bean 인스턴스 생성 전
 */
public class ApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        System.out.println("ApplicationPreparedEvent 발생");
    }
}
