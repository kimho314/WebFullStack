package com.example.authserver.api.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SignupDto {

    public record Request(
            @NotBlank
            @Max(value = 100)
            String userId,
            @NotBlank
            @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
            String password,
            @NotBlank
            @Max(value = 300)
            String name,
            String phoneNumber,
            @Email
            String email
    ) {

    }
}
