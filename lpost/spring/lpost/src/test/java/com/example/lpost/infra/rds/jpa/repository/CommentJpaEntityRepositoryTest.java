package com.example.lpost.infra.rds.jpa.repository;

import com.example.lpost.infra.rds.jpa.AbstractJpaTest;
import com.example.lpost.infra.rds.jpa.config.JpaConfig;
import com.example.lpost.infra.rds.jpa.entity.CommentJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
public class CommentJpaEntityRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("create comment")
    public void createComment() {
        //given
        CommentJpaEntity commentJpaEntity1 = AbstractJpaTest.COMMENT3;

        //when
        CommentJpaEntity saved = commentRepository.save(commentJpaEntity1);

        //then
        assertThat(saved.getId()).isNotNull();
    }
}
