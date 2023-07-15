package com.example.authserver.service;

import com.example.authserver.dto.CreateMemberRequestDto;
import com.example.authserver.entity.Member;
import com.example.authserver.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void create(CreateMemberRequestDto memberRequestDto) {
        Member member = new Member();
        member.setUserId(memberRequestDto.userId());
        member.setPassword(memberRequestDto.password());
        member.setName(memberRequestDto.name());
        member.setEmail(memberRequestDto.email());
        member.setPhoneNumber(memberRequestDto.phoneNumber());
        memberRepository.save(member);
    }
}
