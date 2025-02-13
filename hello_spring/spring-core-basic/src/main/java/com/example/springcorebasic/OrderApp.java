package com.example.springcorebasic;

import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import com.example.springcorebasic.member.MemberService;
import com.example.springcorebasic.order.Order;
import com.example.springcorebasic.order.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = annotationConfigApplicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = annotationConfigApplicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());
    }
}
