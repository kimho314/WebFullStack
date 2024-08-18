package com.example.authserver.api.member;

import com.example.authserver.core.util.TokenProvider;
import com.example.authserver.domain.member.entity.Authority;
import com.example.authserver.domain.member.entity.Member;
import com.example.authserver.domain.member.entity.Token;
import com.example.authserver.domain.member.enums.TokenType;
import com.example.authserver.domain.member.repository.AuthorityRepository;
import com.example.authserver.domain.member.repository.BlackListRepository;
import com.example.authserver.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final BlackListRepository blackListRepository;

    @Transactional
    public void singup(SignupDto.Request request) {
        Optional<Member> maybeMember = memberRepository.findByUserId(request.userId());
        if (maybeMember.isPresent()) {
            throw new IllegalArgumentException(String.format("Member already exists %s", request.userId()));
        }
        String encodedPassword = passwordEncoder.encode(request.password());

        Authority authority = Authority.builder()
                .role(request.role())
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
        log.info("member_id : {}", save.getId());
    }

    @Transactional
    public LoginDto.Response login(LoginDto.Request request) {
        Member member = memberRepository.findByUserId(request.userId())
                .orElseThrow(() -> new NoSuchElementException(request.userId()));
        if (!member.isEnabled()) {
            throw new DisabledException("disabled member");
        }

        boolean isPasswordCorrect = passwordEncoder.matches(request.password(), member.getPassword());
        if (!isPasswordCorrect) {
            throw new BadCredentialsException("invalid password");
        }

        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expireAt = issuedAt.plusSeconds(TokenProvider.ACCESS_TOKEN_EXPIRATION_IN_SECONDS);
        String accessToken = createToken(member, issuedAt, expireAt);
        member.addToken(Token.builder()
                .token(accessToken)
                .tokenType(TokenType.ACCESS)
                .issuedAt(issuedAt)
                .expireAt(expireAt)
                .member(member)
                .build());

        return LoginDto.Response.builder()
                .accessToken(accessToken)
                .build();
    }

    private String createToken(Member member, LocalDateTime issuedAt, LocalDateTime expireAt) {
        String accessToken;
        do {
            accessToken = TokenProvider.create(
                    member.getUserId(),
                    member.getRoles(),
                    issuedAt,
                    expireAt
            );
            log.info("access token : {}", accessToken);

        }
        while (blackListRepository.existsByToken(accessToken));
        return accessToken;
    }

    @Transactional(readOnly = true)
    public GetMemberInfoDto.Response getInfo(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException(userId));

        return GetMemberInfoDto.Response.builder()
                .userId(member.getUserId())
                .email(member.getEmail())
                .role(member.getRoles().get(0))
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    @Transactional
    public void logout(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException(userId));
        Token accessToken = member.getTokens().getFirst();
        member.deleteToken();
        member.addBlackList(accessToken);
    }
}
