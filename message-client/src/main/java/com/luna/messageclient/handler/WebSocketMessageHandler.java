package com.luna.messageclient.handler;

import com.luna.messageclient.dto.Message;
import com.luna.messageclient.json.JsonUtil;
import com.luna.messageclient.service.TerminalService;
import jakarta.websocket.MessageHandler;

public class WebSocketMessageHandler implements MessageHandler.Whole<String>{
    private final TerminalService terminalService;

    public WebSocketMessageHandler(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @Override
    public void onMessage(String s) {
        JsonUtil.fromJson(s, Message.class)
                .ifPresent(message -> terminalService.printMessage(message.username(), message.content()));
    }
}
