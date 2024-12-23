package com.example.hello_jpa.member;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberTest {
//    @Autowired
//    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
//    @Transactional
    void saveTest(){
        Member hoseop = new Member(null, "hoseop", 35);
        em.persist(hoseop);
//        em.flush();
//        em.close();
        em.clear();

        Member member = em.find(Member.class, hoseop.getId());
        assertThat(member).isNotNull();
        assertThat(member).isEqualTo(hoseop);
    }
}
