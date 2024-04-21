package com.example.lunit.api.service;

import com.example.lunit.api.dto.LoginRequestDto;
import com.example.lunit.api.dto.MemberInfoResponseDto;
import com.example.lunit.api.dto.SignupRequestDto;
import com.example.lunit.api.dto.TokenResponseDto;
import com.example.lunit.api.mapper.MemberMapper;
import com.example.lunit.api.mapper.TokenMapper;
import com.example.lunit.common.component.TokenProvider;
import com.example.lunit.common.enums.Role;
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

import java.time.LocalDateTime;
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
    public TokenResponseDto signup(SignupRequestDto signupRequestDto) {
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

        Token saved = createToken(member);
        member.setTokens(Arrays.asList(saved));

        return new TokenResponseDto(saved.getAccessToken());
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

        Token saved = createToken(member);
        member.setTokens(Arrays.asList(saved));

        return new TokenResponseDto(saved.getAccessToken());
    }

    private Token createToken(Member member) {
        String accessToken = tokenProvider.createToken(member.getUsername(), member.getRole(), member.getExpireDuration());
        Token token = TokenMapper.tokenMapper(accessToken, member, tokenProvider.parseToken(accessToken).getExpiration());
        return tokenRepository.save(token);
    }

    @Transactional(readOnly = true)
    public MemberInfoResponseDto getMemberInfo(String userName) {
        Member member = memberRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));


        return new MemberInfoResponseDto(
                member.getUsername(),
                member.getIsEnabled(),
                member.getEmail(),
                Role.mappedToRole(member.getRole()),
                member.getMaxAnalyzeCnt(),
                member.getCurAnalyzeCnt()
        );
    }

    @Transactional
    public void logout(String userName) {
        if (!memberRepository.existsByUserName(userName)) {
            throw new RuntimeException("user not found");
        }
        
        String accessToken = tokenProvider.resolveToken();
        Token token = tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new RuntimeException("token not found"));

        token.setExpiresAt(LocalDateTime.now());
        tokenRepository.save(token);
    }
}
