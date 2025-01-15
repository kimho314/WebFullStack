package com.example.hello_jpa.old_member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DataJpaTest
public class MemberSequenceTest {
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    void memberSeqCreateTest() {
        for (int i = 0; i < 50; i++) {
            MemberSequence memberSequence = new MemberSequence();
            em.persist(memberSequence);
            log.info("memberSequence : {}", memberSequence);
        }
    }
}
