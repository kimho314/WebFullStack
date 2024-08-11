package com.example.authserver.domain.member.repository;

import com.example.authserver.domain.member.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
