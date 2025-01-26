package com.example.hello_jpa.jpa_shop;

import com.example.hello_jpa.jpa_shop.domain.Member;
import com.example.hello_jpa.jpa_shop.domain.Team;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@DataJpaTest
public class TeamMemberTest {
    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void teamAndMemberTest() {
        Member member1 = new Member("hoseop", "seoul", "1", "1111");
        Member member2 = new Member("nilufer", "seoul", "1", "1111");
        Member member3 = new Member("minseop", "seoul", "1", "1111");

        List<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);

        // 연관관계 주인 아닌 쪽에는 값을 넛어도 넛지 않아도 문제가 없다.
        // 양방향 연관관계에서는 양쪽에 값을 넛는것이 권장 됨
//        Team team = new Team("OneTeam");
        Team team = new Team("OneTeam", members);
        em.persist(team);

        member1.addTeam(team); // 연관관계 주인에 꼭 연관관계 값을 저장해야 함!!!
        member2.addTeam(team);
        member3.addTeam(team);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.flush();
        em.clear();

        Team foundTeam = em.find(Team.class, team.getId());
        log.info("===== foundTeam: {} ======", foundTeam);

        List<Member> foundMembers = foundTeam.getMembers();
        for (Member member : foundMembers) {
            log.info("foundMember: {}", member);
        }
    }
}
