package com.example.lpost.web.dto;

public record CreateCommentReqDto(Long postId, String contents, String author) {
}
