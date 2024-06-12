package com.example.core.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> STORE = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        STORE.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return STORE.get(memberId);
    }
}
