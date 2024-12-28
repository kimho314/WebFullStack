package com.example.hello_jpa.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class MemberTest {
    //    @Autowired
//    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    void saveTest() {
        Member hoseop = new Member(null, "hoseop", 35);
        em.persist(hoseop);
//        em.flush();
//        em.close();
        em.clear();

        Member member = em.find(Member.class, hoseop.getId());
        assertThat(member).isNotNull();
        assertThat(member).isEqualTo(hoseop);
    }

    @Test
    @Transactional
    void equalTest() {
        Member member = new Member("hoseop", 35);
        em.persist(member);

        Member foundMember1 = em.find(Member.class, member.getId());
        Member foundMember2 = em.find(Member.class, member.getId());
        assertThat(foundMember1).isEqualTo(foundMember2); // 동일성 보장
    }

    @Test
    @Transactional
    @Rollback(false)
    void writeBehindTest() {
        log.info(em.getFlushMode().name());
        Member member1 = new Member("hoseop", 35);
        em.persist(member1);
        log.info("member1 persist");
        Member member2 = new Member("hoseop", 36);
        em.persist(member2);
        log.info("member2 persist");

        // 예상 결과 : "member2 persist" 후에 insert sql 날라가는 것을 예상
        // 실제 결과 : 각각의 member1, member2 persist후에 insert sql 날라감??? why???
    }
}
