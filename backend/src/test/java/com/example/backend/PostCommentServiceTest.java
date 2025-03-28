package com.example.backend;

import com.example.backend.entity.PostComment;
import com.example.backend.entity.User;
import com.example.backend.repository.PostCommentRepository;
import com.example.backend.service.PostCommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostCommentServiceTest {

    @Mock
    private PostCommentRepository postCommentRepository;

    @InjectMocks
    private PostCommentService postCommentService;

    private PostComment testPostComment;
    private PostComment testChildComment;
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
        testPostComment.setParent(null);

        testChildComment = new PostComment();
        testChildComment.setId(2L);
        testChildComment.setUserId(testUser);
        testChildComment.setTitle("Test Comment");
        testChildComment.setText("This is a test comment");
        testChildComment.setDateTime(LocalDateTime.now());
        testChildComment.setParent(testPostComment);
    }

    @Test
    void testIsPost() {
        assertTrue(postCommentService.isPost(testPostComment));
        assertFalse(postCommentService.isPost(testChildComment));
    }

    @Test
    void testGetAll() {
        List<PostComment> mockComments = new ArrayList<>();
        mockComments.add(testPostComment);
        mockComments.add(testChildComment);

        when(postCommentRepository.findAll()).thenReturn(mockComments);

        List<PostComment> result = postCommentService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(testPostComment));
        assertTrue(result.contains(testChildComment));
    }

    @Test
    void testAddPostComment() {
        when(postCommentRepository.save(testPostComment)).thenReturn(testPostComment);

        PostComment result = postCommentService.addPostComment(testPostComment);

        assertEquals(testPostComment, result);
        verify(postCommentRepository, times(1)).save(testPostComment);
    }

    @Test
    void testFindById() {
        when(postCommentRepository.findById(1L)).thenReturn(Optional.of(testPostComment));

        PostComment result = postCommentService.findById(1L);

        assertEquals(testPostComment, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(postCommentRepository.findById(999L)).thenReturn(Optional.empty());

        PostComment result = postCommentService.findById(999L);

        assertNull(result);
    }

    @Test
    void testDeleteById() {
        doNothing().when(postCommentRepository).deleteById(1L);

        String result = postCommentService.deleteById(1L);

        assertEquals("The user with id = 1 was deleted succesfully", result);
        verify(postCommentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteByIdWithException() {
        doThrow(new RuntimeException("Delete failed")).when(postCommentRepository).deleteById(1L);

        String result = postCommentService.deleteById(1L);

        assertEquals("There was an error when deleting user with id = 1", result);
    }

    @Test
    void testGetParentById() {
        when(postCommentRepository.findById(2L)).thenReturn(Optional.of(testChildComment));

        PostComment result = postCommentService.getParentById(2L);

        assertEquals(testPostComment, result);
    }

    @Test
    void testGetAllPosts() {
        List<PostComment> mockComments = new ArrayList<>();
        mockComments.add(testPostComment);
        mockComments.add(testChildComment);

        when(postCommentRepository.findAll()).thenReturn(mockComments);

        List<PostComment> result = postCommentService.getAllPosts();

        assertEquals(1, result.size());
        assertTrue(result.contains(testPostComment));
        assertFalse(result.contains(testChildComment));
    }

    @Test
    void testGetAllComments() {
        List<PostComment> mockComments = new ArrayList<>();
        mockComments.add(testPostComment);
        mockComments.add(testChildComment);

        when(postCommentRepository.findAll()).thenReturn(mockComments);

        List<PostComment> result = postCommentService.getAllComments();

        assertEquals(1, result.size());
        assertTrue(result.contains(testChildComment));
        assertFalse(result.contains(testPostComment));
    }
}
