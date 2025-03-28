package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vote")
@IdClass(VoteId.class)
public class Vote {
    //ca sa avem o cheie compusa, facem o noua clasa publica care trebuie sa aibe un contructor gol, sa implementam equals() si hashCode(), si sa fie serializabila
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "type")
    private String type;

    public Vote() {
    }

    public Vote(Long userId, Long postId, String type) {
        this.userId = userId;
        this.postId = postId;
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
