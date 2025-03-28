package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post-tag")
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posttag_id")
    private Long id;


    private Long idTag;

    private Long idPost;

    public PostTag() {
    }

    public PostTag(Long id, Long idTag, Long idPost) {
        this.id = id;
        this.idTag = idTag;
        this.idPost = idPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
        this.idTag = idTag;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }
}
