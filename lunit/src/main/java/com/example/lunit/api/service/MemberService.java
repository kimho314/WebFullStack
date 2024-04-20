package com.example.lunit.api.service;

import com.example.lunit.api.dto.LoginRequestDto;
import com.example.lunit.api.dto.SignupRequestDto;
import com.example.lunit.api.dto.TokenResponseDto;
import com.example.lunit.api.mapper.MemberMapper;
import com.example.lunit.api.mapper.TokenMapper;
import com.example.lunit.common.component.TokenProvider;
import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.entity.Token;
import com.example.lunit.domain.repository.MemberRepository;
import com.example.lunit.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void signup(SignupRequestDto signupRequestDto) {
        if (memberRepository.existsByUserName(signupRequestDto.userName())) {
            throw new RuntimeException("member exists");
        }

        Member member = MemberMapper.signupMapper(
                signupRequestDto.userName(),
                passwordEncoder.encode(signupRequestDto.password()),
                signupRequestDto.role(),
                signupRequestDto.email()
        );

        memberRepository.save(member);
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto request) {
        if (!memberRepository.existsByUserName(request.userName())) {
            throw new UsernameNotFoundException(request.userName() + "not found");
        }

        Member member = memberRepository.findByUserName(request.userName())
                .orElseThrow(() -> new RuntimeException("user not found"));
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new BadCredentialsException("password is incorrect");
        }

        String accessToken = tokenProvider.createToken(member.getUsername(), member.getRole(), member.getExpireDuration());
        Token token = TokenMapper.tokenMapper(accessToken, member);
        Token saved = tokenRepository.save(token);

        return new TokenResponseDto(saved.getAccessToken());
    }
}
