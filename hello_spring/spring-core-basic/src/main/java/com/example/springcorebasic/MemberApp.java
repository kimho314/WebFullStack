package com.example.springcorebasic;

import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import com.example.springcorebasic.member.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = annotationConfigApplicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + member.getName());
        System.out.println("find Member : " + findMember.getName());
    }
}
