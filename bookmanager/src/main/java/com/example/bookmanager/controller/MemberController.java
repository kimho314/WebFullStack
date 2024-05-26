package com.example.bookmanager.controller;

import com.example.bookmanager.dto.GetMemberDto;
import com.example.bookmanager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/{userId}")
    public ResponseEntity<GetMemberDto> getMember(@PathVariable String userId) {
        GetMemberDto response = memberService.getMember(userId);
        return ResponseEntity.ok(response);
    }
}
