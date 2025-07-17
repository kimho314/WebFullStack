package com.example.lpost.infra.rds.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class CommentJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String contents;
    @Column(length = 100)
    private String author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostJpaEntity post;

    protected CommentJpaEntity() {
    }

    public CommentJpaEntity(String contents, String author) {
        this(null, contents, author, null);
    }

    public CommentJpaEntity(Long id, String contents, String author, PostJpaEntity post) {
        super();
        this.id = id;
        this.contents = contents;
        this.author = author;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public String getAuthor() {
        return author;
    }

    public PostJpaEntity getPost() {
        return post;
    }

    public void addPost(PostJpaEntity postJpaEntity) {
        this.post = postJpaEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CommentJpaEntity commentJpaEntity = (CommentJpaEntity) o;
        return id.equals(commentJpaEntity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
