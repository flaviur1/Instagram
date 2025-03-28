package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post-tag")
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posttag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag idTag;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostComment idPost;

    public PostTag() {
    }

    public PostTag(Long id, Tag idTag, PostComment idPost) {
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

    public Tag getIdTag() {
        return idTag;
    }

    public void setIdTag(Tag idTag) {
        this.idTag = idTag;
    }

    public PostComment getIdPost() {
        return idPost;
    }

    public void setIdPost(PostComment idPost) {
        this.idPost = idPost;
    }
}
