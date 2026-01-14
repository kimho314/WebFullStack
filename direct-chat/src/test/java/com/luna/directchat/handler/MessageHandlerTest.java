package com.luna.directchat.handler;

import com.luna.directchat.DirectChatApplication;
import com.luna.directchat.dto.Message;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(
        classes = DirectChatApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageHandlerTest {
    @LocalServerPort
    int port;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void DirectChatBasicTest() throws ExecutionException, InterruptedException, IOException {
        // given:
        String url = "ws://localhost:"+port+"/ws/v1/message";
        BlockingQueue<String> leftQueue = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> rightQueue = new ArrayBlockingQueue<>(1);

        var leftClient = new StandardWebSocketClient();
        var leftWebSocketSession = leftClient.execute(new TextWebSocketHandler() {
            @Override
            protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
                leftQueue.put(message.getPayload());
            }
        }, url).get();

        var rightClient = new StandardWebSocketClient();
        var rightWebSocketSession = rightClient.execute(new TextWebSocketHandler() {
            @Override
            protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
                rightQueue.put(message.getPayload());
            }
        }, url).get();

        // when:
        leftWebSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(new Message("안녕하세요."))));
        rightWebSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(new Message("Hello."))));

        // then:
        rightQueue.poll(1, TimeUnit.SECONDS).contains("안녕하세요.");

        leftQueue.poll(1, TimeUnit.SECONDS).contains("Hello.");

        leftWebSocketSession.close();
        rightWebSocketSession.close();
    }
}
