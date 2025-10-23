package com.blog.blogapp.controller;

import com.blog.blogapp.JwtUtil;
import com.blog.blogapp.repository.UserRepository;
import com.blog.blogapp.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password");

        when(userRepository.existsByUsername("john")).thenReturn(false);

        ResponseEntity<String> response = authController.register(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testLoginUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Mock stored user in DB (password already hashed)
        User storedUser = new User();
        storedUser.setUsername("john");
        storedUser.setPassword(encoder.encode("password"));

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(storedUser));
        when(jwtUtil.generateToken("john")).thenReturn("mockToken");

        // Simulate login request (raw password)
        User loginUser = new User();
        loginUser.setUsername("john");
        loginUser.setPassword("password");

        ResponseEntity<String> response = authController.login(loginUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mockToken", response.getBody());
    }

}

