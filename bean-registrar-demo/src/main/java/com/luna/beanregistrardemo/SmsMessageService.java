package com.luna.beanregistrardemo;

import java.time.LocalDateTime;

public class SmsMessageService implements MessageService{
    @Override
    public String getMessage() {
        return "SMS message set at " + LocalDateTime.now();
    }

    @Override
    public String getServiceType() {
        return ServiceType.SMS.name();
    }
}
