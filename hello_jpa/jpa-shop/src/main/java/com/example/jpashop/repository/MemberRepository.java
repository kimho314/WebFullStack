package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private final EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
