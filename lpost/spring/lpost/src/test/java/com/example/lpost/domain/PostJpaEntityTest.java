package com.example.lpost.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class PostJpaEntityTest {
    @Test
    @DisplayName("create post")
    void createPost() {
        Long id = 1L;
        String title = "title";
        String contents = "contents";
        String author = "author";
        Post post = new Post(id, title, contents, author, LocalDateTime.now());

        assertThat(post).isNotNull();
        assertThat(post.id()).isEqualTo(id);
        assertThat(post.title()).isEqualTo(title);
        assertThat(post.contents()).isEqualTo(contents);
        assertThat(post.author()).isEqualTo(author);
    }
}
