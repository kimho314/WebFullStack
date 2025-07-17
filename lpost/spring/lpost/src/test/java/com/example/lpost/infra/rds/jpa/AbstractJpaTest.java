package com.example.lpost.infra.rds.jpa;

import com.example.lpost.infra.rds.jpa.entity.CommentJpaEntity;
import com.example.lpost.infra.rds.jpa.entity.PostJpaEntity;

public class AbstractJpaTest {
    public static final PostJpaEntity POST1 = new PostJpaEntity("title1", "contents1", "author1");
    public static final PostJpaEntity POST2 = new PostJpaEntity("title2", "contents2", "author2");

    public static final CommentJpaEntity COMMENT1 = new CommentJpaEntity("contents1", "author1");
    public static final CommentJpaEntity COMMENT2 = new CommentJpaEntity("contents2", "author2");
    public static final CommentJpaEntity COMMENT3 = new CommentJpaEntity("contents3", "author3");
}
