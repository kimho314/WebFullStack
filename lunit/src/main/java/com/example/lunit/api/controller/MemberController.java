package com.example.lunit.api.controller;

import com.example.lunit.api.dto.LoginRequestDto;
import com.example.lunit.api.dto.MemberInfoResponseDto;
import com.example.lunit.api.dto.SignupRequestDto;
import com.example.lunit.api.dto.TokenResponseDto;
import com.example.lunit.api.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/signup")
    public ResponseEntity<TokenResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(memberService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(memberService.login(request));
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout(Principal principal) {
        String userName = principal.getName();
        if (!StringUtils.hasText(userName)) {
            throw new UsernameNotFoundException("user not found");
        }

        memberService.logout(userName);

        return ResponseEntity.ok(null);
    }


    @GetMapping("/member-info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(Principal principal) {
        String userName = principal.getName();
        if (!StringUtils.hasText(userName)) {
            throw new UsernameNotFoundException("user not found");
        }

        return ResponseEntity.ok(memberService.getMemberInfo(userName));
    }
}
