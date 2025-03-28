package com.example.backend;

import com.example.backend.controller.UserController;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User(1L, "testuser", "password", "test@example.com", "1234567890", 0L, false, false);
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = Arrays.asList(testUser);
        when(userService.getUsers()).thenReturn(mockUsers);

        List<User> result = userController.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(testUser, result.get(0));
        verify(userService, times(1)).getUsers();
    }

    @Test
    void testAddUser() {
        when(userService.addUser(testUser)).thenReturn(testUser);

        User result = userController.addUser(testUser);

        assertEquals(testUser, result);
        verify(userService, times(1)).addUser(testUser);
    }

    @Test
    void testFindUserById() {
        when(userService.findById(1L)).thenReturn(testUser);

        User result = userController.findUserById(1L);

        assertEquals(testUser, result);
        verify(userService, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        when(userService.deleteById(1L)).thenReturn("The user with id = 1 was deleted succesfully").thenReturn("The user with id = 1 was deleted succesfully");

        String result = userController.deleteById(1L);

        assertEquals("The user with id = 1 was deleted succesfully", result);
        verify(userService, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User(1L, "updateduser", "newpassword", "updated@example.com", "0987654321", 10L, true, false);
        when(userService.addUser(updatedUser)).thenReturn(updatedUser);

        User result = userController.updateUser(updatedUser);

        assertEquals(updatedUser, result);
        verify(userService, times(1)).addUser(updatedUser);
    }
}
