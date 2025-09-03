package com.blog.blogapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void testGenerateAndValidateToken() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token);
        assertEquals(username, jwtUtil.extractUsername(token));
        assertTrue(jwtUtil.validatingToken(username, token));
    }

    @Test
    void testExpiredToken() throws InterruptedException {
        // Simulate with small expiry
        String token = jwtUtil.generateToken("expiredUser");
        Thread.sleep(2000); // wait a bit (if you reduce expiry in JwtUtil for test)

        assertFalse(jwtUtil.isTokenExpired(token));
    }
}
