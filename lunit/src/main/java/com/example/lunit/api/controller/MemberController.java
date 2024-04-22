package com.example.lunit.api.controller;

import com.example.lunit.api.dto.*;
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

    /**
     * @param signupRequestDto
     * @return
     * @title 회원 가입
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<TokenResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(memberService.signup(signupRequestDto));
    }

    /**
     * @param request
     * @return
     * @title 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(memberService.login(request));
    }

    /**
     * @param principal
     * @return
     * @title 로그아웃
     */
    @PutMapping("/logout")
    public ResponseEntity<Void> logout(Principal principal) {
        String userName = principal.getName();
        if (!StringUtils.hasText(userName)) {
            throw new UsernameNotFoundException("user not found");
        }

        memberService.logout(userName);

        return ResponseEntity.ok(null);
    }

    /**
     * @param request
     * @return
     * @@title 토큰 재발급
     */
    @PutMapping("/reissue-token")
    public ResponseEntity<ReissueTokenResponseDto> reissueToken(@Valid @RequestBody ReissueTokenRequestDto request) {
        return ResponseEntity.ok(memberService.reissueToken(request));
    }

    /**
     * @param principal
     * @return
     * @title 회원 정보 조회
     */
    @GetMapping("/member-info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(Principal principal) {
        String userName = principal.getName();
        if (!StringUtils.hasText(userName)) {
            throw new UsernameNotFoundException("user not found");
        }

        return ResponseEntity.ok(memberService.getMemberInfo(userName));
    }

    /**
     * @param principal
     * @return
     * @title 회원 탈퇴
     */
    @DeleteMapping("/signout")
    public ResponseEntity<Void> signout(Principal principal) {
        memberService.sigout(principal.getName());
        return ResponseEntity.ok(null);
    }
}
