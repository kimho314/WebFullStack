package com.example.authserver.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateMemberRequestDto(
        @NotNull @Size(max = 12, message = "유저 아이디는 12자 이하로 입력해주세요.") String userId,
        @NotNull @Size(min = 8, max = 12, message = "패스워드는 8~12 사이로 입력해주세요.") String password,
        @NotNull @Size(max = 255, message = "이름은 255자 이하로 입력해주세요.") String name,
        @Size(max = 12, message = "핸드폰 번호는 12자 이하로 입력해주세요.") String phoneNumber,
        @Size(max = 320, message = "이메일은 320자 이하로 입력해주세요.") String email
) {
}
