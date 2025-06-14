package com.example.backend.service;

import com.example.backend.entity.PostComment;
import com.example.backend.repository.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public Boolean isPost(PostComment postComment) {
        if (postComment.getParent() == null)
            return true;
        return false;
    }

    public List<PostComment> getAll() {
        return (List<PostComment>) this.postCommentRepository.findAll();
    }

    public PostComment addPostComment(PostComment postComment) {
        postComment.setDateTime(LocalDateTime.now());
        return this.postCommentRepository.save(postComment);
    }

    public PostComment findById(Long id) {
        return this.postCommentRepository.findById(id).isPresent() ? this.postCommentRepository.findById(id).get() : null;
    }


    public String deleteById(Long id) {
        try {
            this.postCommentRepository.deleteById(id);
            return "The user with id = " + id + " was deleted succesfully";
        } catch (Exception e) {
            return "There was an error when deleting user with id = " + id;
        }
    }

    public PostComment getParentById(Long id) {
        return findById(id).getParent();
    }

    public List<PostComment> getAllPosts() {
        ArrayList<PostComment> list = (ArrayList<PostComment>) this.postCommentRepository.findAll();
        list.removeIf(post -> !isPost(post));
        return list;
    }

    public List<PostComment> getAllComments() {
        ArrayList<PostComment> list = (ArrayList<PostComment>) this.postCommentRepository.findAll();
        list.removeIf(post -> isPost(post));
        return list;
    }

    public List<PostComment> getAllPostsByUser(Long id) {
        ArrayList<PostComment> allPostComments = (ArrayList<PostComment>) this.postCommentRepository.findAll();
        return allPostComments.stream()
                .filter(postComment ->
                        isPost(postComment) &&
                                postComment.getUserId() != null &&
                                postComment.getUserId().getId().equals(id)
                )
                .collect(Collectors.toList());
    }

    public PostComment addComment(PostComment postComment, Long id) {
        PostComment postCommentFound = this.postCommentRepository.findById(id).isPresent() ? this.postCommentRepository.findById(id).get() : null;
        postComment.setParent(postCommentFound);
        postComment.setDateTime(LocalDateTime.now());
        return this.postCommentRepository.save(postComment);
    }

    public PostComment addScoreToPostComment(Long id) {
        if (this.postCommentRepository.findById(id).isPresent()) {
            if (this.postCommentRepository.findById(id).get().getScore() == null)
                this.postCommentRepository.findById(id).get().setScore(0L);
            this.postCommentRepository.findById(id).get().setScore(this.postCommentRepository.findById(id).get().getScore() + 1);
            this.postCommentRepository.save(this.postCommentRepository.findById(id).get());
        }
        return this.postCommentRepository.findById(id).isPresent() ? this.postCommentRepository.findById(id).get() : null;
    }

    public PostComment minusScoreToPostComment(Long id) {
        if (this.postCommentRepository.findById(id).isPresent()) {
            if (this.postCommentRepository.findById(id).get().getScore() == null)
                this.postCommentRepository.findById(id).get().setScore(0L);
            this.postCommentRepository.findById(id).get().setScore(this.postCommentRepository.findById(id).get().getScore() - 1);
            this.postCommentRepository.save(this.postCommentRepository.findById(id).get());
        }
        return this.postCommentRepository.findById(id).isPresent() ? this.postCommentRepository.findById(id).get() : null;
    }
}

