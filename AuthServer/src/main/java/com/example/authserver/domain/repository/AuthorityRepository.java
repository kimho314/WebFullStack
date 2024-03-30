package com.example.authserver.domain.repository;

import com.example.authserver.domain.entity.Authority;
import com.example.authserver.domain.entity.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
}
