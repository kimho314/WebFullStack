package com.example.authserver;

import com.example.authserver.entity.Member;
import com.example.authserver.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void saveMemberTest() {
        Member member = Member.builder()
                .email("hoseop.kim@gmail.com")
                .userId("khs")
                .password("12345")
                .phoneNumber("01029597794")
                .build();
        Member saved = memberRepository.save(member);
        Assertions.assertEquals(member.getUserId(), saved.getUserId());
    }
}
