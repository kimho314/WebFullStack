package com.luna.messageclient.handler;

import com.luna.messageclient.dto.Message;
import com.luna.messageclient.json.JsonUtil;
import com.luna.messageclient.service.TerminalService;
import jakarta.websocket.Session;

import java.io.IOException;

public class WebSocketSender {
    private final TerminalService terminalService;

    public WebSocketSender(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    public void sendMessage(Session session, Message message) {
        if (session != null && session.isOpen()) {
            JsonUtil.toJson(message)
                    .ifPresent(
                            msg -> {
                                try {
                                    session.getBasicRemote().sendText(msg);
                                } catch (IOException ex) {
                                    terminalService.printSystemMessage(
                                            String.format("%s send failed: %s", message, ex.getMessage()));
                                }
                            });
        }
    }
}
