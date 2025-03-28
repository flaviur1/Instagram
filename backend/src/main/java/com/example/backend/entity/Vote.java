package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vote")
@IdClass(VoteId.class)
public class Vote {
    //ca sa avem o cheie compusa, facem o noua clasa publica care trebuie sa aibe un contructor gol, sa implementam equals() si hashCode(), si sa fie serializabila
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @OneToOne
    @JoinColumn(name = "postComment_id")
    private PostComment postComment_id;

    @Column(name = "type")
    private String type;

    public Vote() {
    }

    public Vote(User userId, PostComment postComment_id, String type) {
        this.userId = userId;
        this.postComment_id = postComment_id;
        this.type = type;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public PostComment getPostCommentId() {
        return postComment_id;
    }

    public void setPostCommentId(PostComment postId) {
        this.postComment_id = postId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
