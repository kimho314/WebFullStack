package com.example.lunit.api.service;

import com.example.lunit.api.dto.SignupRequestDto;
import com.example.lunit.api.dto.SignupResponseDto;
import com.example.lunit.api.mapper.MemberMapper;
import com.example.lunit.api.mapper.TokenMapper;
import com.example.lunit.common.component.TokenProvider;
import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.entity.Token;
import com.example.lunit.domain.repository.MemberRepository;
import com.example.lunit.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("user not found"));
        if (!member.getIsEnabled()) {
            throw new RuntimeException("user not enabled");
        }

        return member;
    }

    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        if (memberRepository.existsByUserName(signupRequestDto.userName())) {
            throw new RuntimeException("member exists");
        }

        Member member = MemberMapper.signupMapper(signupRequestDto.userName(), signupRequestDto.password(), signupRequestDto.role());

        String accessToken = tokenProvider.createToken(signupRequestDto.userName(), signupRequestDto.role(), MemberMapper.DEFAULT_EXPIRE_DURATION);
        Token token = TokenMapper.tokenMapper(accessToken, member);

        member.setTokens(Arrays.asList(token));

        memberRepository.save(member);
        tokenRepository.save(token);

        return new SignupResponseDto(accessToken);
    }
}
