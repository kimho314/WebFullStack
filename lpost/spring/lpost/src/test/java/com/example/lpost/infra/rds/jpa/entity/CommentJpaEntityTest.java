package com.example.lpost.infra.rds.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentJpaEntityTest {
    @Test
    @DisplayName("create comment entity")
    void createComment() {
        Long id = 1L;
        String contents = "test";
        String author = "nil";
        CommentJpaEntity commentJpaEntity = new CommentJpaEntity(id, contents, author, null);

        assertThat(commentJpaEntity).isNotNull();
        assertThat(commentJpaEntity.getId()).isEqualTo(id);
        assertThat(commentJpaEntity.getContents()).isEqualTo(contents);
        assertThat(commentJpaEntity.getAuthor()).isEqualTo(author);
        assertThat(commentJpaEntity.getPost()).isNull();
    }
}
