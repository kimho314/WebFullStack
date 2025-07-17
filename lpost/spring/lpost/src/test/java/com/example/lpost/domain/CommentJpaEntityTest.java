package com.example.lpost.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentJpaEntityTest {
    @Test
    @DisplayName("create comment")
    void createComment() {
        Long id = 1L;
        String contents = "contents";
        String author = "hoseop";
        Long postId = 1L;
        Comment comment = new Comment(id, postId, contents, author, LocalDateTime.now());

        assertThat(comment).isNotNull();
        assertThat(comment.id()).isEqualTo(id);
        assertThat(comment.contents()).isEqualTo(contents);
        assertThat(comment.author()).isEqualTo(author);
        assertThat(comment.postId()).isEqualTo(postId);
    }
}
