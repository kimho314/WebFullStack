package com.example.lunit.api.service;

import com.example.lunit.api.dto.*;
import com.example.lunit.api.mapper.MemberMapper;
import com.example.lunit.api.mapper.TokenMapper;
import com.example.lunit.common.component.TokenProvider;
import com.example.lunit.common.enums.ResultStatus;
import com.example.lunit.common.enums.Role;
import com.example.lunit.common.enums.TokenType;
import com.example.lunit.common.exception.MemberNotFoundException;
import com.example.lunit.common.exception.ServiceException;
import com.example.lunit.domain.entity.Member;
import com.example.lunit.domain.entity.Token;
import com.example.lunit.domain.repository.MemberRepository;
import com.example.lunit.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
                .orElseThrow(() -> new MemberNotFoundException());
        if (!member.getIsEnabled()) {
            throw new RuntimeException("user not enabled");
        }

        return member;
    }

    @Transactional
    public TokenResponseDto signup(SignupRequestDto signupRequestDto) {
        if (memberRepository.existsByUserNameAndIsEnabled(signupRequestDto.userName(), true)) {
            throw new MemberNotFoundException();
        }

        Member member = MemberMapper.signupMapper(
                signupRequestDto.userName(),
                passwordEncoder.encode(signupRequestDto.password()),
                signupRequestDto.role(),
                signupRequestDto.email()
        );

        memberRepository.save(member);

        Token saved = createToken(member, TokenType.SIGNUP);
        member.setTokens(Arrays.asList(saved));

        return new TokenResponseDto(ResultStatus.SUCCESS, HttpStatus.OK.value(), saved.getJwtToken(), null);
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto request) {
        if (!memberRepository.existsByUserNameAndIsEnabled(request.userName(), true)) {
            throw new MemberNotFoundException();
        }

        Member member = memberRepository.findByUserName(request.userName())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "member exists"));
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "password is incorrect");
        }

        Token savedAccessToken = createToken(member, TokenType.ACCESS);
        Token savedRefreshToken = createToken(member, TokenType.REFRESH);
        member.setTokens(Arrays.asList(savedAccessToken, savedRefreshToken));

        tokenRepository.deleteByMemberAndTokenType(member, TokenType.SIGNUP);

        return new TokenResponseDto(ResultStatus.SUCCESS, HttpStatus.OK.value(), savedAccessToken.getJwtToken(), savedRefreshToken.getJwtToken());
    }

    private Token createToken(Member member, TokenType tokenType) {
        String accessToken = tokenProvider.createToken(
                member.getUsername(),
                member.getRole(),
                TokenType.REFRESH.equals(tokenType) ? TokenProvider.DEFAULT_REFRESH_EXPIRE_DURATION : member.getExpireDuration()
        );
        Token token = TokenMapper.tokenMapper(
                accessToken,
                member,
                tokenProvider.parseToken(accessToken).getExpiration(),
                tokenType
        );
        return tokenRepository.save(token);
    }

    @Transactional(readOnly = true)
    public MemberInfoResponseDto getMemberInfo(String userName) {
        Member member = memberRepository.findByUserName(userName)
                .orElseThrow(() -> new MemberNotFoundException());


        return new MemberInfoResponseDto(
                ResultStatus.SUCCESS,
                HttpStatus.OK.value(),
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
        if (!memberRepository.existsByUserNameAndIsEnabled(userName, true)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "member exists");
        }

        String accessToken = tokenProvider.resolveToken();
        Token token = tokenRepository.findByJwtToken(accessToken)
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "token not found"));
        if (TokenType.SIGNUP.equals(token.getTokenType())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "invalid token type");
        }

        token.setExpiresAt(LocalDateTime.now());
        tokenRepository.save(token);
    }

    @Transactional
    public ReissueTokenResponseDto reissueToken(ReissueTokenRequestDto request) {
        Member member = memberRepository.findByUserName(request.userName())
                .orElseThrow(() -> new MemberNotFoundException());

        Token token = tokenRepository.findByJwtToken(request.token())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND.value(), "token not found"));

        String newToken = tokenProvider.createToken(member.getUsername(), member.getRole(), TokenType.REFRESH.equals(token.getTokenType()) ? TokenProvider.DEFAULT_REFRESH_EXPIRE_DURATION : member.getExpireDuration());
        token.setJwtToken(newToken);
        token.setExpiresAt(tokenProvider.parseToken(newToken).getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        return new ReissueTokenResponseDto(ResultStatus.SUCCESS, HttpStatus.OK.value(), newToken, token.getTokenType());
    }

    @Transactional
    public void sigout(String name) {
        Member member = memberRepository.findByUserName(name)
                .orElseThrow(() -> new MemberNotFoundException());
        if (!member.getIsEnabled()) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(), "member is disabled");
        }

        tokenRepository.deleteAllByMember(member);

        member.setIsEnabled(Boolean.FALSE);
    }
}
