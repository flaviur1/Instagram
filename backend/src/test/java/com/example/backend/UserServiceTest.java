package com.example.backend;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User(1L, "testuser", "password", "test@example.com", "1234567890", 0L, false, false);
    }

    @Test
    void testGetUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(testUser);

        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userService.getUsers();

        assertEquals(1, result.size());
        assertTrue(result.contains(testUser));
    }

    @Test
    void testAddUser() {
        when(userRepository.save(testUser)).thenReturn(testUser);

        User result = userService.addUser(testUser);

        assertEquals(testUser, result);
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        User result = userService.findById(1L);

        assertEquals(testUser, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        User result = userService.findById(999L);

        assertNull(result);
    }

    @Test
    void testDeleteById() {
        doNothing().when(userRepository).deleteById(1L);

        String result = userService.deleteById(1L);

        assertEquals("The user with id = 1 was deleted succesfully", result);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteByIdWithException() {
        doThrow(new RuntimeException("Delete failed")).when(userRepository).deleteById(1L);

        String result = userService.deleteById(1L);

        assertEquals("There was an error when deleting user with id = 1", result);
    }
}
