package com.example.lpost.infra.rds.jpa.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PostJpaEntityTest {
    @Test
    @DisplayName("create post entity")
    void createPost() {
        Long id = 1L;
        String title = "title";
        String contents = "test";
        String author = "hoseop";
        PostJpaEntity postJpaEntity = new PostJpaEntity(id, title, contents, author, new ArrayList<>());

        assertThat(postJpaEntity).isNotNull();
        assertThat(postJpaEntity.getId()).isEqualTo(id);
        assertThat(postJpaEntity.getTitle()).isEqualTo(title);
        assertThat(postJpaEntity.getContents()).isEqualTo(contents);
        assertThat(postJpaEntity.getAuthor()).isEqualTo(author);
        assertThat(postJpaEntity.getComments()).isEmpty();
    }
}