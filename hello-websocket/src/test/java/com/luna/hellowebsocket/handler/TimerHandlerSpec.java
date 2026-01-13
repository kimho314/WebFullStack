package com.luna.hellowebsocket.handler;

import com.luna.hellowebsocket.HelloWebsocketApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = HelloWebsocketApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimerHandlerSpec {
    @LocalServerPort
    private int port;

    @Test
    @DisplayName("timer 동작 테스트")
    void timerTest() throws ExecutionException, InterruptedException, IOException {
        //given
        String url = "ws://localhost:" + port + "/ws/timer";
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketSession webSocketSession = client.execute(new TextWebSocketHandler() {
            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                queue.put(message.getPayload());
            }
        }, url).get();

        //when
        webSocketSession.sendMessage(new TextMessage("2"));

        //then
        queue.poll(1, TimeUnit.SECONDS).contains("등록 완료");
        queue.poll(5, TimeUnit.SECONDS).contains("타이머 완료");

        webSocketSession.close();
    }
}
