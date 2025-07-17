package com.example.lpost.domain;

import com.example.lpost.infra.rds.jpa.entity.PostJpaEntity;

import java.time.LocalDateTime;

public record Post(Long id, String title, String contents, String author, LocalDateTime createdAt) {
    public Post(String title, String contents, String author) {
        this(null, title, contents, author, null);
    }

    public PostJpaEntity toEntity(){
        return new PostJpaEntity(title, contents, author);
    }

    public static Post toDomain(PostJpaEntity postJpaEntity){
        return new Post(postJpaEntity.getId(), postJpaEntity.getTitle(), postJpaEntity.getContents(), postJpaEntity.getAuthor(), postJpaEntity.getCreatedAt());
    }
}
