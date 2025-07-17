package com.example.lpost.web.service;

import com.example.lpost.common.exception.NoPostException;
import com.example.lpost.domain.Comment;
import com.example.lpost.infra.rds.jpa.config.JpaConfig;
import com.example.lpost.infra.rds.jpa.entity.CommentJpaEntity;
import com.example.lpost.infra.rds.jpa.repository.CommentRepository;
import com.example.lpost.web.dto.CreateCommentReqDto;
import com.example.lpost.web.dto.CreatePostReqDto;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({CommentService.class, PostService.class, JpaConfig.class})
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    @Autowired
    CommentRepository commentRepository;

    private Long postId;

    void savePost() {
        CreatePostReqDto createPostReqDto = new CreatePostReqDto("elice", "this is book", "nil");
        postId = postService.createPost(createPostReqDto);
    }

    @Test
    @DisplayName("create comment")
    void createComment() {
        Result result = saveComment();

        //then
        CommentJpaEntity found = commentRepository.findById(result.commentId()).orElseThrow();

        assertThat(found.getId()).isEqualTo(result.commentId());
        assertThat(found.getContents()).isEqualTo(result.contents());
        assertThat(found.getAuthor()).isEqualTo(result.author());
    }

    private Result saveComment() {
        //given
        savePost();

        String contents = "test";
        String author = "hoseop";
        CreateCommentReqDto createCommentReqDto = new CreateCommentReqDto(postId, contents, author);

        //when
        Long commentId = commentService.createComment(createCommentReqDto);
        return new Result(contents, author, commentId);
    }

    private record Result(String contents, String author, Long commentId) {
    }

    @Test
    @DisplayName("create comment - post not found error")
    void createComment_postNotFound() {
        //given
        String contents = "test";
        String author = "hoseop";
        CreateCommentReqDto createCommentReqDto = new CreateCommentReqDto(3L, contents, author);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> thrown = assertThatThrownBy(() -> commentService.createComment(createCommentReqDto));

        //then
        thrown.isInstanceOf(NoPostException.class);
        thrown.hasMessageContaining("3");
    }

    @Test
    @DisplayName("read comment")
    void readAllCommentByPostId() {
        //given
        Result result = saveComment();

        //when
        List<Comment> comments = commentService.findAll(postId);

        //then
        assertThat(comments).hasSize(1);
        Comment first = comments.getFirst();
        assertThat(first.id()).isEqualTo(result.commentId());
        assertThat(first.contents()).isEqualTo(result.contents());
        assertThat(first.author()).isEqualTo(result.author());
    }
}
