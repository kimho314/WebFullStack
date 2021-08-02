package com.sp.fc.controller;

import lombok.*;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SecurityMessage {

    private Authentication auth;
    private String message;

    @Builder
    public SecurityMessage(Authentication auth, String message) {
        this.auth = auth;
        this.message = message;
    }
}
