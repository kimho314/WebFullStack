package com.example.authserver.controller;

import com.example.authserver.dto.CreateMemberRequestDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MemberController {
    @PostMapping("/member")
    public void createMember(@RequestBody @Valid CreateMemberRequestDto request) {
        return;
    }

}
