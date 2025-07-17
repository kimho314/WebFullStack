package com.example.lpost.infra.rds.jpa.repository;

import com.example.lpost.infra.rds.jpa.AbstractJpaTest;
import com.example.lpost.infra.rds.jpa.config.JpaConfig;
import com.example.lpost.infra.rds.jpa.entity.CommentJpaEntity;
import com.example.lpost.infra.rds.jpa.entity.PostJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
public class PostJpaEntityRepositoryTest {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("create post entity")
    public void createAndReadPost() {
        //given
        PostJpaEntity postJpaEntity1 = AbstractJpaTest.POST1;
        CommentJpaEntity commentJpaEntity1 = AbstractJpaTest.COMMENT1;
        CommentJpaEntity commentJpaEntity2 = AbstractJpaTest.COMMENT2;

        //when
        PostJpaEntity saved = postRepository.saveAndFlush(postJpaEntity1);
        saved.addComments(commentJpaEntity1, commentJpaEntity2);
        List<CommentJpaEntity> savedCommentJpaEntities = commentRepository.saveAll(saved.getComments());
        em.flush();
        em.clear();

        PostJpaEntity found = postRepository.findById(saved.getId()).orElseThrow();
        List<CommentJpaEntity> commentJpaEntities = found.getComments();

        //then
        assertThat(saved).isEqualTo(postJpaEntity1);
        assertThat(found).isEqualTo(saved);
        assertThat(commentJpaEntities).containsAnyElementsOf(savedCommentJpaEntities);
    }
}
