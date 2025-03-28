package com.example.backend.entity;

import java.io.Serializable;

public class VoteId implements Serializable {
    private Long userId;
    private Long postComment_id;

    public VoteId() {
    }

    public VoteId(Long userId, Long postComment_id) {
        this.userId = userId;
        this.postComment_id = postComment_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof VoteId))
            return false;

        VoteId aux = (VoteId) obj;
        return this.userId.equals(aux.userId) && this.postComment_id.equals(aux.postComment_id);
    }

    @Override
    public int hashCode() {
        int hash = 100;
        hash = 42 * hash + userId.hashCode() + postComment_id.hashCode();

        return hash;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostCommentId() {
        return postComment_id;
    }

    public void setPostCommentId(Long postId) {
        this.postComment_id = postId;
    }
}
