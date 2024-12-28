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
        // reason : 엔터티가 영속 상태가 되려면 식별자가 꼭 필요하다.
        // 그런데 식별자 생성전략을 identity로 사용하면 데이터베이스에 실제로 저장르 해야 식별자를 수할 수 있으므로
        // insert 쿼리가 즉시 데이터베이스에 전달된다.
        // 따라서 이 경우에 쓰기 지연을 활용한 성능 최적화를 할 수 없다.
        // 2024-12-28T22:22:54.864+09:00 DEBUG 1572 --- [hello_jpa] [    Test worker] org.hibernate.engine.spi.ActionQueue     : Executing identity-insert immediately
    }

    @Test
    @Transactional
    @Rollback(false)
    void writeBehindTest2() {
        Member2 member1 = new Member2(1L, "hoseop", 35);
        em.persist(member1);
        log.info("member1 persist");
        Member2 member2 = new Member2(2L, "hoseop", 36);
        em.persist(member2);
        log.info("member2 persist");

        // 식별자 생성전략을 identity를 사용하지 않는 Member2를 사용했을때 쓰기 전략이 예상하는대로 실행되는 것으 확인할 수 있다.
    }
}
