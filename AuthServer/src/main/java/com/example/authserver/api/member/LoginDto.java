package com.example.authserver.api.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

public class LoginDto {

    @Builder
    public record Request(@NotEmpty String userId,
                          @NotEmpty String password) {

    }

    @Builder
    public record Response(String accessToken) {

    }
}
