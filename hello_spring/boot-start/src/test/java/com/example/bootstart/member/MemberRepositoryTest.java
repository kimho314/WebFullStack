package com.example.bootstart.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Test
    void memberTest() {
        Member member = new Member("idA", "memberA");
        memberRepository.initTable();
        memberRepository.save(member);
        Member foundMember = memberRepository.find(member.getMemberId());
        assertThat(foundMember.getName()).isEqualTo(member.getName());
        assertThat(foundMember.getMemberId()).isEqualTo(member.getMemberId());
    }
}
