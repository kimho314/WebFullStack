package com.example.lunit.api.dto;

import com.example.lunit.common.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record SignupRequestDto(
        @Length(min = 1, max = 100) String userName,
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$") String password,
        @NotNull Role role
) {
}
