package com.example.springcorebasic;

import com.example.springcorebasic.discount.FixDiscountPolicy;
import com.example.springcorebasic.member.MemberService;
import com.example.springcorebasic.member.MemberServiceImpl;
import com.example.springcorebasic.member.MemoryMemberRepository;
import com.example.springcorebasic.order.OrderService;
import com.example.springcorebasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
