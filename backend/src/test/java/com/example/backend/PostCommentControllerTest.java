package com.example.backend;

import com.example.backend.controller.PostCommentController;
import com.example.backend.entity.PostComment;
import com.example.backend.entity.User;
import com.example.backend.service.PostCommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostCommentControllerTest {

    @Mock
    private PostCommentService postCommentService;

    @InjectMocks
    private PostCommentController postCommentController;

    private PostComment testPostComment;
    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User(1L, "testuser", "password", "test@example.com", "1234567890", 0L, false, false);

        testPostComment = new PostComment();
        testPostComment.setId(1L);
        testPostComment.setUserId(testUser);
        testPostComment.setTitle("Test Post");
        testPostComment.setText("This is a test post");
        testPostComment.setDateTime(LocalDateTime.now());
    }

    @Test
    void testGetAll() {
        List<PostComment> mockComments = Arrays.asList(testPostComment);
        when(postCommentService.getAll()).thenReturn(mockComments);

        List<PostComment> result = postCommentController.getAll();

        assertEquals(1, result.size());
        assertEquals(testPostComment, result.get(0));
        verify(postCommentService, times(1)).getAll();
    }

    @Test
    void testAddPostComment() {
        when(postCommentService.addPostComment(testPostComment)).thenReturn(testPostComment);

        PostComment result = postCommentController.addPostComment(testPostComment);

        assertEquals(testPostComment, result);
        verify(postCommentService, times(1)).addPostComment(testPostComment);
    }

    @Test
    void testFindById() {
        when(postCommentService.findById(1L)).thenReturn(testPostComment);

        PostComment result = postCommentController.findPostCommentById(1L);

        assertEquals(testPostComment, result);
        verify(postCommentService, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        when(postCommentService.deleteById(1L)).thenReturn("The user with id = 1 was deleted succesfully");

        String result = postCommentController.deleteById(1L);

        assertEquals("The user with id = 1 was deleted succesfully", result);
        verify(postCommentService, times(1)).deleteById(1L);
    }

    @Test
    void testPut() {
        when(postCommentService.addPostComment(testPostComment)).thenReturn(testPostComment);

        PostComment result = postCommentController.put(testPostComment);

        assertEquals(testPostComment, result);
        verify(postCommentService, times(1)).addPostComment(testPostComment);
    }

    @Test
    void testGetParentById() {
        PostComment parentComment = new PostComment();
        parentComment.setId(2L);
        when(postCommentService.getParentById(1L)).thenReturn(parentComment);

        PostComment result = postCommentController.getParentById(1L);

        assertEquals(parentComment, result);
        verify(postCommentService, times(1)).getParentById(1L);
    }

    @Test
    void testIsPost() {
        when(postCommentService.findById(1L)).thenReturn(testPostComment);
        when(postCommentService.isPost(testPostComment)).thenReturn(true);

        Boolean result = postCommentController.isPost(1L);

        assertTrue(result);
        verify(postCommentService, times(1)).findById(1L);
        verify(postCommentService, times(1)).isPost(testPostComment);
    }

    @Test
    void testGetAllPosts() {
        List<PostComment> mockPosts = Arrays.asList(testPostComment);
        when(postCommentService.getAllPosts()).thenReturn(mockPosts);

        List<PostComment> result = postCommentController.getAllPosts();

        assertEquals(1, result.size());
        assertEquals(testPostComment, result.get(0));
        verify(postCommentService, times(1)).getAllPosts();
    }

    @Test
    void testGetAllComments() {
        PostComment childComment = new PostComment();
        childComment.setId(2L);
        childComment.setParent(testPostComment);

        List<PostComment> mockComments = Arrays.asList(childComment);
        when(postCommentService.getAllComments()).thenReturn(mockComments);

        List<PostComment> result = postCommentController.getAllComments();

        assertEquals(1, result.size());
        assertEquals(childComment, result.get(0));
        verify(postCommentService, times(1)).getAllComments();
    }
}
