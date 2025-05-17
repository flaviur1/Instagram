package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<User> getAllUsers() {
        return this.userService.getUsers();
    }

    @PostMapping("/add")
    @ResponseBody
    public User addUser(User user) {
        return this.userService.addUser(user);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public User findUserById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseBody
    public String deleteById(@PathVariable Long id) {
        return this.userService.deleteById(id);
    }

    @PutMapping("/put")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}
