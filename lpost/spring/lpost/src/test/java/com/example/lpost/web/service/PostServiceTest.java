package com.example.lpost.web.service;

import com.example.lpost.domain.Post;
import com.example.lpost.infra.rds.jpa.config.JpaConfig;
import com.example.lpost.infra.rds.jpa.entity.PostJpaEntity;
import com.example.lpost.infra.rds.jpa.repository.PostRepository;
import com.example.lpost.web.dto.CreatePostReqDto;
import com.example.lpost.web.dto.ReadPostReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import({ PostService.class, JpaConfig.class})
public class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;


    @Test
    @DisplayName("create post")
    void createPost(){
        Result result = savePost();

        //then
        PostJpaEntity found = postRepository.findById(result.postId()).orElseThrow();

        assertThat(found.getId()).isEqualTo(result.postId());
        assertThat(found.getTitle()).isEqualTo(result.createPostReqDto().title());
        assertThat(found.getContents()).isEqualTo(result.createPostReqDto().contents());
        assertThat(found.getAuthor()).isEqualTo(result.createPostReqDto().author());
        assertThat(found.getCreatedAt()).isNotNull();
    }

    private Result savePost() {
        //given
        CreatePostReqDto createPostReqDto = new CreatePostReqDto("elice", "this is book", "nil");

        //when
        Long postId = postService.createPost(createPostReqDto);
        return new Result(createPostReqDto, postId);
    }

    private record Result(CreatePostReqDto createPostReqDto, Long postId) {
    }

    @Test
    @DisplayName("read post")
    void readPost(){
        //given
        Result result = savePost();
        ReadPostReqDto req = new ReadPostReqDto(result.postId());

        //when
        Post post = postService.findById(req);

        //then
        assertThat(post.id()).isEqualTo(result.postId());
    }
}
