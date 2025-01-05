package com.example.hello_jpa.member;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void flushTest() {
        MemberIdentity memberIdentity = new MemberIdentity("test", 35);
        MemberIdentity savedMember = memberRepository.saveAndFlush(memberIdentity);
        savedMember.changeName("modified_test");
        log.info("===== name has changed =====");
        // 만약 findByMemberId가 없었으면 "modified_test" 가 테스트 끝날때 반영된다.
        // findByMemberId 메소드가 savedMember에 영향을 주기 때문에 flush 일어남.
        MemberIdentity foundMember = memberRepository.findByMemberId(savedMember.getId()).orElseThrow();
        log.info("found member: {}", foundMember);
    }
}
