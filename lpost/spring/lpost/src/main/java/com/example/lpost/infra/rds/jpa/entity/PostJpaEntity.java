package com.example.lpost.infra.rds.jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
public class PostJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(length = 200)
    private String title;
    private String contents;
    @Column(length = 100)
    private String author;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<CommentJpaEntity> commentJpaEntities = new ArrayList<>();

    protected PostJpaEntity() {
    }

    public PostJpaEntity(String title, String contents, String author) {
        this(null, title, contents, author, new ArrayList<>());
    }

    public PostJpaEntity(Long id, String title, String contents, String author, List<CommentJpaEntity> commentJpaEntities) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.commentJpaEntities = commentJpaEntities;
    }

    public void addComments(CommentJpaEntity... commentJpaEntities) {
        for (CommentJpaEntity commentJpaEntity : commentJpaEntities) {
            commentJpaEntity.addPost(this);
            this.commentJpaEntities.add(commentJpaEntity);
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getAuthor() {
        return author;
    }

    public List<CommentJpaEntity> getComments() {
        return commentJpaEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PostJpaEntity postJpaEntity = (PostJpaEntity) o;
        return id.equals(postJpaEntity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
