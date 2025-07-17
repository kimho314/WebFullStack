package com.example.lpost.domain;

import com.example.lpost.infra.rds.jpa.entity.CommentJpaEntity;

import java.time.LocalDateTime;

public record Comment(Long id, Long postId, String contents, String author, LocalDateTime createdAt) {
    public Comment(Long postId, String contents, String author) {
        this(null, postId, contents, author, null);
    }

    public CommentJpaEntity toEntity() {
        return new CommentJpaEntity(contents, author);
    }

    public static Comment toDomain(CommentJpaEntity commentJpaEntity) {
        return new Comment(commentJpaEntity.getId(), commentJpaEntity.getPost().getId(), commentJpaEntity.getContents(), commentJpaEntity.getAuthor(), commentJpaEntity.getCreatedAt());
    }


}
