package com.example.lunit.domain.repository;

import com.example.lunit.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserName(String username);

    boolean existsByUserNameAndIsEnabledAndRole(String username, Boolean isEnabled, String role);

    boolean existsByUserNameAndIsEnabled(String username, Boolean isEnabled);

    List<Member> findAllByRole(String role);
}
