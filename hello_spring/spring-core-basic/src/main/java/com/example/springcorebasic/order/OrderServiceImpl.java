package com.example.springcorebasic.order;

import com.example.springcorebasic.annotation.MainDiscountPolicy;
import com.example.springcorebasic.discount.DiscountPolicy;
import com.example.springcorebasic.member.Member;
import com.example.springcorebasic.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //    @Autowired // 생성자가 1개일 때는 constructor에 autowired annotation 불필요
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
