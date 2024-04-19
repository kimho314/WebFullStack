package com.example.lunit.domain.repository;

import com.example.lunit.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
