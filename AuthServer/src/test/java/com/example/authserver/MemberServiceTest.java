package com.example.authserver;

import com.example.authserver.core.enums.Role;
import com.example.authserver.domain.entity.Authority;
import com.example.authserver.domain.entity.Member;
import com.example.authserver.domain.repository.AuthorityRepository;
import com.example.authserver.domain.repository.MemberRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Test
    @Transactional
    @SneakyThrows
    void saveMemberTest() {
        final String userId = "hoseop";
        Authority authority = Authority.builder()
                .authorityId(new AuthorityId(userId, Role.ROLE_USER.name()))
                .build();

        Member member = Member.builder()
                .userId(userId)
                .password("1234")
                .name(userId)
                .authorities(List.of(authority))
                .build();

        Member saveMember = memberRepository.save(member);
        Authority saveAuthority = authorityRepository.save(authority);

        Optional<Member> maybeMember = memberRepository.findById(saveMember.getId());
        Assertions.assertTrue(maybeMember.isPresent());
        Optional<Authority> maybeAuthority = authorityRepository.findById(saveAuthority.getAuthorityId());
        Assertions.assertTrue(maybeAuthority.isPresent());
    }
}
