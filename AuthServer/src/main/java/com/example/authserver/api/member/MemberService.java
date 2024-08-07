package com.example.authserver.api.member;

import com.example.authserver.core.enums.Role;
import com.example.authserver.core.util.TokenProvider;
import com.example.authserver.domain.member.entity.Authority;
import com.example.authserver.domain.member.entity.Member;
import com.example.authserver.domain.member.repository.AuthorityRepository;
import com.example.authserver.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
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

    @Transactional
    public void singup(SignupDto.Request request) {
        Optional<Member> maybeMember = memberRepository.findByUserId(request.userId());
        if (maybeMember.isPresent()) {
            throw new IllegalArgumentException(String.format("Member already exists %s", request.userId()));
        }
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
        log.info("member_id : {}", save.getId());
    }

    @Transactional
    public LoginDto.Response login(LoginDto.Request request) {
        Member member = memberRepository.findByUserId(request.userId())
                .orElseThrow(() -> new NoSuchElementException(request.userId()));

        if (!member.isEnabled()) {
            throw new AuthenticationServiceException("disabled member");
        }

        boolean isPasswordCorrect = passwordEncoder.matches(request.password(), member.getPassword());
        if (!isPasswordCorrect) {
            throw new AuthenticationServiceException("invalid password");
        }

        String accessToken = TokenProvider.create(
                member.getUserId(),
                member.getRoles(),
                LocalDateTime.now(),
                LocalDateTime.now().plusSeconds(TokenProvider.ACCESS_TOKEN_EXPIRATION_IN_SECONDS)
        );
        log.info("access token : {}", accessToken);
        return LoginDto.Response.builder()
                .accessToken(accessToken)
                .build();
    }
}
