package com.example.authserver.api.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<LoginDto.Response> login(@RequestBody LoginDto.Request request) {
        LoginDto.Response response = memberService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/logout", produces = "application/json")
    public ResponseEntity<Void> logout(Principal principal) {
        memberService.logout(principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/member", produces = "application/json")
    public ResponseEntity<GetMemberInfoDto.Response> getMember(Principal principal) {
        GetMemberInfoDto.Response memberInfo = memberService.getInfo(principal.getName());
        return ResponseEntity.ok(memberInfo);
    }
}
