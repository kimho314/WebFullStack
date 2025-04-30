package com.example.helloservlet.web.frontcontroller.v4.controller;

import com.example.helloservlet.domain.member.Member;
import com.example.helloservlet.domain.member.MemberRepository;
import com.example.helloservlet.web.frontcontroller.v4.ControllerV4;
import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        Member saved = memberRepository.save(member);
        model.put("member", saved);

        return "save-result";
    }
}
