package com.example.backend.service;

import com.example.backend.entity.MyUserDetails;
import com.example.backend.repository.UserRepository;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        User user2 = userRepository.findByEmail(username);
        if (user == null && user2 == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        if (user == null)
            return new MyUserDetails(user2);
        else
            return new MyUserDetails(user);
    }
}
