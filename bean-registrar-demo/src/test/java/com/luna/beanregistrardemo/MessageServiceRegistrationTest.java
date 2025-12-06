package com.luna.beanregistrardemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(properties = "app.message-type=email")
class MessageServiceRegistrationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldRegisterEmailMessageService() {
        MessageService service = context.getBean("messageService", MessageService.class);

        assertThat(service).isInstanceOf(EmailMessageService.class);
        assertThat(service.getServiceType()).isEqualTo("EMAIL");
    }
}
