package com.example.backend.controller;

import com.example.backend.entity.PostComment;
import com.example.backend.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostCommentController {
    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<PostComment> getAll() {
        return this.postCommentService.getAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public PostComment addPostComment(@RequestBody PostComment postComment) {
        return this.postCommentService.addPostComment(postComment);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public PostComment findPostCommentById(@PathVariable Long id) {
        return this.postCommentService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseBody
    public String deleteById(@PathVariable Long id) {
        return this.postCommentService.deleteById(id);
    }

    @PutMapping("/put")
    @ResponseBody
    public PostComment put(PostComment postComment) {
        return this.postCommentService.addPostComment(postComment);
    }

    @GetMapping("/getParent/{id}")
    @ResponseBody
    public PostComment getParentById(@PathVariable Long id) {
        return this.postCommentService.getParentById(id);
    }

    @GetMapping("/isPostById/{id}")
    @ResponseBody
    public Boolean isPost(@PathVariable Long id) {
        return this.postCommentService.isPost(findPostCommentById(id));
    }

    @GetMapping("/getAllPosts")
    @ResponseBody
    public List<PostComment> getAllPosts() {
        return this.postCommentService.getAllPosts();
    }

    @GetMapping("/getAllComments")
    @ResponseBody
    public List<PostComment> getAllComments() {
        return this.postCommentService.getAllComments();
    }

    @GetMapping("/getAllPostsByUser/{id}")
    @ResponseBody
    public List<PostComment> getAllPostsByUser(@PathVariable Long id) {
        return this.postCommentService.getAllPostsByUser(id);
    }

    @PostMapping("/addComment/{id}")
    @ResponseBody
    public PostComment addComment(@PathVariable Long id, @RequestBody PostComment postComment) {
        return this.postCommentService.addComment(postComment, id);
    }
}
