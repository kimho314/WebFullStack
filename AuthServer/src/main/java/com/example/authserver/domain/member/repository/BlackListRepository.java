package com.example.authserver.domain.member.repository;

import com.example.authserver.domain.member.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    boolean existsByToken(String token);
}
