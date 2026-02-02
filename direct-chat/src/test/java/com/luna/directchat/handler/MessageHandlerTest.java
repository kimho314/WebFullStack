package com.luna.directchat.handler;

import com.luna.directchat.DirectChatApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(
        classes = DirectChatApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageHandlerTest {
    @LocalServerPort
    int port;

    ObjectMapper objectMapper = new ObjectMapper();

}
