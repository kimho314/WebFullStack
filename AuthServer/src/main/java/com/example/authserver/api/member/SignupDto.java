package com.example.authserver.api.member;

import com.example.authserver.domain.member.enums.Role;
import jakarta.validation.constraints.*;

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
            String email,
            @NotNull
            Role role
    ) {

    }
}
