package com.luna.directchat.dto.websocket.inbound;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.luna.directchat.constant.MessageType;

public class KeepAliveRequest extends BaseRequest{
    @JsonCreator
    public KeepAliveRequest() {
        super(MessageType.KEEP_ALIVE);
    }
}
