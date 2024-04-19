package com.example.lunit.api.controller;

import com.example.lunit.api.dto.SignupRequestDto;
import com.example.lunit.api.dto.SignupResponseDto;
import com.example.lunit.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        SignupResponseDto result = memberService.signup(signupRequestDto);
        return ResponseEntity.ok(result);
    }
}
