package com.luna.grafanamonitoringdemo.service;

import com.luna.grafanamonitoringdemo.dto.GetMemberReqDto;
import com.luna.grafanamonitoringdemo.dto.GetMemberRespDto;
import com.luna.grafanamonitoringdemo.dto.SaveMemberReqDto;
import com.luna.grafanamonitoringdemo.entity.Member;
import com.luna.grafanamonitoringdemo.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private static final Logger log = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    @CachePut(value = "member", key = "#saveMemberReqDto.name()")
    public void save(SaveMemberReqDto saveMemberReqDto){
        Member member = new Member(saveMemberReqDto.name(),
            saveMemberReqDto.password(),
            saveMemberReqDto.email());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "member", key = "#getMemberReqDto.id()")
    public GetMemberRespDto findById(GetMemberReqDto getMemberReqDto){
        return memberRepository.findById(getMemberReqDto.id())
            .map(it -> new GetMemberRespDto(it.getId(),
                it.getName(),
                it.getPassword(),
                it.getEmail()))
            .orElseThrow(() -> new RuntimeException("Member not found"));
    }
}
