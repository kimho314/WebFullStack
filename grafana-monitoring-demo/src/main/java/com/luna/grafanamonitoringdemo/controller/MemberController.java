package com.luna.grafanamonitoringdemo.controller;

import com.luna.grafanamonitoringdemo.dto.SaveMemberReqDto;
import com.luna.grafanamonitoringdemo.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(name = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody SaveMemberReqDto saveMemberReqDto){
        memberService.save(saveMemberReqDto);
        return ResponseEntity.ok().build();
    }
}
