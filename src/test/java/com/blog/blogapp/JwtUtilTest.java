package com.blog.blogapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtilTest {

    private JwtUtil jwtUtil;
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        userDetails = new User("testuser", "password", Collections.emptyList());
    }

    @Test
    public void testGenerateTokenAndExtractUsername() {
        String token = jwtUtil.generateToken(userDetails.getUsername());
        assertNotNull(token);

        String username = jwtUtil.extractUsername(token);
        assertEquals(userDetails.getUsername(), username);
    }

    @Test
    public void testValidateToken() {
        String token = jwtUtil.generateToken(userDetails.getUsername());
        boolean isValid = jwtUtil.validateToken(token, userDetails);
        assertTrue(isValid, "Token should be valid for the given user");
    }
}
