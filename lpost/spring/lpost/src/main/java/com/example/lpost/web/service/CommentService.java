package com.example.lpost.web.service;

import com.example.lpost.common.exception.NoPostException;
import com.example.lpost.domain.Comment;
import com.example.lpost.infra.rds.jpa.entity.CommentJpaEntity;
import com.example.lpost.infra.rds.jpa.entity.PostJpaEntity;
import com.example.lpost.infra.rds.jpa.repository.CommentRepository;
import com.example.lpost.infra.rds.jpa.repository.PostRepository;
import com.example.lpost.web.dto.CreateCommentReqDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Transactional
    public Long createComment(CreateCommentReqDto createCommentReqDto) {
        Comment comment = new Comment(createCommentReqDto.postId(), createCommentReqDto.contents(), createCommentReqDto.author());
        CommentJpaEntity commentJpaEntity = comment.toEntity();

        PostJpaEntity postJpaEntity = postRepository.findById(createCommentReqDto.postId())
                .orElseThrow(() -> new NoPostException(createCommentReqDto.postId().toString()));

        commentJpaEntity.addPost(postJpaEntity);
        CommentJpaEntity save = commentRepository.save(commentJpaEntity);

        return save.getId();
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll(Long postId) {
        PostJpaEntity postJpaEntity = postRepository.findById(postId)
                .orElseThrow(() -> new NoPostException(postId.toString()));

        List<Comment> comments = commentRepository.findAllByPost_Id(postJpaEntity.getId()).stream()
                .map(Comment::toDomain)
                .toList();

        return comments;
    }
}
