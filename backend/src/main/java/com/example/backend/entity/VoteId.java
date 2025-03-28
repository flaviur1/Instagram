package com.example.backend.entity;

import java.io.Serializable;

public class VoteId implements Serializable {
    private Long userId;
    private Long postId;

    public VoteId() {
    }

    public VoteId(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof VoteId))
            return false;

        VoteId aux = (VoteId) obj;
        return this.userId.equals(aux.userId) && this.postId.equals(aux.postId);
    }

    @Override
    public int hashCode() {
        int hash = 100;
        hash = 42 * hash + userId.hashCode() + postId.hashCode();

        return hash;
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
}
