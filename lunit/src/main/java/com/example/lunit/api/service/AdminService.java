package com.example.lunit.api.service;

import com.example.lunit.api.dto.GetAnalyzeResultResponseDto;
import com.example.lunit.api.dto.GetMembersResponseDto;
import com.example.lunit.common.enums.Role;
import com.example.lunit.common.exception.MemberNotFoundException;
import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.repository.DicomAnalyzeResultRepository;
import com.example.lunit.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final MemberRepository memberRepository;
    private final DicomAnalyzeResultRepository dicomAnalyzeResultRepository;

    @Transactional(readOnly = true)
    public List<GetMembersResponseDto> getMembers() {
        return memberRepository.findAllByRole(Role.mappedToAuthority(Role.CLIENT)).stream()
                .map(it -> GetMembersResponseDto.builder()
                        .userName(it.getUsername())
                        .email(it.getEmail())
                        .isEnabled(it.getIsEnabled())
                        .maxAnalyzeCnt(it.getMaxAnalyzeCnt())
                        .curAnalyzeCnt(it.getCurAnalyzeCnt())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GetAnalyzeResultResponseDto> getAnalyzeResult(String userName) {
        Member member = memberRepository.findByUserName(userName)
                .orElseThrow(MemberNotFoundException::new);

        return dicomAnalyzeResultRepository.findAllByMemberOrderByCreatedAtDesc(member).stream()
                .map(it -> GetAnalyzeResultResponseDto.builder()
                        .frontalScore(it.getFrontalScore())
                        .predictionTime(it.getPredictionTime())
                        .createdAt(it.getCreatedAt().toString())
                        .build())
                .collect(Collectors.toList());
    }
}
