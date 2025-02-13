package com.example.springcorebasic.discount;


import com.example.springcorebasic.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
