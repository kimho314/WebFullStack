package com.example.authserver.api.auth;

import com.example.authserver.domain.member.entity.Member;
import com.example.authserver.domain.member.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        User user = new User(
                member.getUserId(),
                member.getPassword(),
                member.isEnabled(),
                true,
                true,
                true,
                member.getRoles().stream()
                        .map(it -> new SimpleGrantedAuthority(it.name()))
                        .toList()
        );
        return user;
    }
}
