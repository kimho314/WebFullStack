package com.example.authserver.dto;

public record CreateMemberRequestDto(String userId, String password, String phoneNumber, String email) {
}
