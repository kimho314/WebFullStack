package com.example.bookmanager.repository;


import com.example.bookmanager.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    void saveMemberTest() {
        Member member = Member.builder()
                .password("test password")
                .userId("test id")
                .name("test user")
                .build();
        Member save = memberRepository.save(member);
        Assertions.assertNotNull(save.getId());
        Assertions.assertEquals("test id", save.getUserId());
        Assertions.assertEquals("test user", save.getName());
        Assertions.assertEquals("test password", save.getPassword());
    }

    @Test
    void findMemberByNameTest() {
        Member member = Member.builder()
                .password("test password")
                .userId("test id")
                .name("test user")
                .build();
        Member save = memberRepository.saveAndFlush(member);
        em.clear();

        Member foundMember = memberRepository.findById(save.getId()).orElseThrow();
        Assertions.assertEquals(save.getName(), foundMember.getName());
    }
}
