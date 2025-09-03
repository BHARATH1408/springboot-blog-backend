package com.blog.blogapp.integration;

import com.blog.blogapp.JwtUtil;
import com.blog.blogapp.user.User;
import com.blog.blogapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private String jwtToken;

    @BeforeEach
    void setUp() {
        // Clean DB
        userRepository.deleteAll();

        // Create test user in DB
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        userRepository.save(user);

        // Generate token for this user
        jwtToken = jwtUtil.generateToken("testuser");
    }

    @Test
    void testAccessProtectedEndpointWithoutToken() throws Exception {
        mockMvc.perform(get("/posts")) // 🚨 assume /posts is protected
                .andExpect(status().isForbidden()); // Expect 403 Forbidden
    }

    @Test
    void testAccessProtectedEndpointWithValidToken() throws Exception {
        mockMvc.perform(get("/posts")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // ✅ Should pass
    }

    @Test
    void testAccessProtectedEndpointWithInvalidToken() throws Exception {
        mockMvc.perform(get("/posts")
                        .header("Authorization", "Bearer fakeToken"))
                .andExpect(status().isForbidden());
    }
}
