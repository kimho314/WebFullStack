package com.example.lpost.infra.rds.jpa.repository;

import com.example.lpost.infra.rds.jpa.entity.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostJpaEntity, Long> {
}
