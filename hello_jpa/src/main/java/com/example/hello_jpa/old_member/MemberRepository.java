package com.example.hello_jpa.old_member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberIdentity, Long> {
    @Query(value = "SELECT * FROM member_identity WHERE id = :id", nativeQuery = true)
    Optional<MemberIdentity> findByMemberId(@Param("id") Long id);
}
