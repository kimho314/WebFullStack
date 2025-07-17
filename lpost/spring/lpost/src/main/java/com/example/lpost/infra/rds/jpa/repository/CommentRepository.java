package com.example.lpost.infra.rds.jpa.repository;

import com.example.lpost.infra.rds.jpa.entity.CommentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentJpaEntity, Long> {
    List<CommentJpaEntity> findAllByPost_Id(Long postId);
}
