package com.luna.springapplicationevent;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;

public class EnvPreparedListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println();
        String[] profiles = event.getEnvironment().getActiveProfiles();
        String name = event.getEnvironment().getProperty("spring.application.name");
        System.out.println("ApplicationEnvironmentPreparedEvent 발생 - profiles : " + Arrays.toString(profiles) + " name : " + name);
    }
}
