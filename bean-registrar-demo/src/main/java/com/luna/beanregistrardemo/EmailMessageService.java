package com.luna.beanregistrardemo;

import java.time.LocalDateTime;

public class EmailMessageService implements MessageService{
    @Override
    public String getMessage() {
        return "Email message sent at " + LocalDateTime.now();
    }

    @Override
    public String getServiceType() {
        return ServiceType.EMAIL.name();
    }
}
