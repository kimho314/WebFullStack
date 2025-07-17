package com.example.lpost.web.service;

import com.example.lpost.common.exception.NoPostException;
import com.example.lpost.domain.Post;
import com.example.lpost.infra.rds.jpa.entity.PostJpaEntity;
import com.example.lpost.infra.rds.jpa.repository.PostRepository;
import com.example.lpost.web.dto.CreatePostReqDto;
import com.example.lpost.web.dto.ReadPostReqDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Long createPost(CreatePostReqDto createPostReqDto){
        Post post = new Post(createPostReqDto.title(), createPostReqDto.contents(), createPostReqDto.author());
        PostJpaEntity saved = postRepository.save(post.toEntity());
        return saved.getId();
    }

    @Transactional(readOnly = true)
    public Post findById(ReadPostReqDto req) {
        PostJpaEntity postJpaEntity = postRepository.findById(req.postId())
                .orElseThrow(() -> new NoPostException(req.postId().toString()));
        return Post.toDomain(postJpaEntity);
    }
}
