package com.example.authserver.api.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<Void> signup(@RequestBody SignupDto.Request request) {
        memberService.singup(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/member/{userId}", produces = "application/json")
    public ResponseEntity<String> getMember(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userId);
    }
}
