package com.example.bookmanager.service;

import com.example.bookmanager.dto.GetMemberDto;
import com.example.bookmanager.entity.Member;
import com.example.bookmanager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public GetMemberDto getMember(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException(userId + " not found"));

        return GetMemberDto.builder()
                .userId(member.getUserId())
                .name(member.getName())
                .build();
    }
}
