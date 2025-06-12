package com.example.basicjpa.old_member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberIdentity, Long> {

    @Query(value = "SELECT * FROM member_identity WHERE id = :id", nativeQuery = true)
    Optional<MemberIdentity> findByMemberId(@Param("id") Long id);
}
