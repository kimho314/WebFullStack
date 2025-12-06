package com.luna.beanregistrardemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/message")
class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public MessageResponse sendMessage(){
        return new MessageResponse(
                messageService.getMessage(), messageService.getServiceType()
        );
    }
}
