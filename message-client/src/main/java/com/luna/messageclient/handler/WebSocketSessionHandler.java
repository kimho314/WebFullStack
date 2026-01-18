package com.luna.messageclient.handler;

import com.luna.messageclient.service.TerminalService;
import jakarta.websocket.CloseReason;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.Session;

public class WebSocketSessionHandler extends Endpoint {
    private final TerminalService terminalService;

    public WebSocketSessionHandler(TerminalService terminalService) {
        this.terminalService = terminalService;
    }
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        terminalService.printSystemMessage("WebSocket Connected.");
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        terminalService.printSystemMessage("Connection closed: " + closeReason.getReasonPhrase());
    }

    @Override
    public void onError(Session session, Throwable thr) {
        terminalService.printSystemMessage("Error: " + thr.getMessage());
    }
}
