package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vote")
@IdClass(VoteId.class)
public class Vote {
    //ca sa avem o cheie compusa, facem o noua clasa publica care trebuie sa aibe un contructor gol, sa implementam equals() si hashCode(), si sa fie serializabila
    @Id
    @Column(name = "user_id")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @Column(name = "post_id")
    @OneToOne
    @JoinColumn(name = "postComment_id")
    private PostComment postId;

    @Column(name = "type")
    private String type;

    public Vote() {
    }

    public Vote(User userId, PostComment postId, String type) {
        this.userId = userId;
        this.postId = postId;
        this.type = type;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public PostComment getPostId() {
        return postId;
    }

    public void setPostId(PostComment postId) {
        this.postId = postId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
