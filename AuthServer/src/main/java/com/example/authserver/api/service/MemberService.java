package com.example.authserver.api.service;

import com.example.authserver.api.dto.SignupDto;
import com.example.authserver.core.enums.Role;
import com.example.authserver.domain.entity.Authority;
import com.example.authserver.domain.entity.Member;
import com.example.authserver.domain.repository.AuthorityRepository;
import com.example.authserver.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public String singup(SignupDto.Request request) {
        String encodedPassword = passwordEncoder.encode(request.password());

        Authority authority = Authority.builder()
                .role(Role.ROLE_USER)
                .build();
        Member member = Member.builder()
                .userId(request.userId())
                .password(encodedPassword)
                .name(request.name())
                .phoneNumber(request.phoneNumber())
                .email(request.email())
                .authorities(List.of(authority))
                .build();
        Member save = memberRepository.save(member);

        authority.setMember(member);
        authorityRepository.save(authority);

        return save.getUserId();
    }
}
