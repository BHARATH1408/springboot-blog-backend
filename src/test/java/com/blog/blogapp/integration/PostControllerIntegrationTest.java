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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void testAccessPublicEndpointWithoutToken() throws Exception {
        // ✅ Public GET endpoint, should return 200 OK
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
    }

    @Test
    void testAccessPublicEndpointWithValidToken() throws Exception {
        // ✅ Should also work fine with a valid token
        mockMvc.perform(get("/posts")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testAccessPublicEndpointWithInvalidToken() throws Exception {
        // ✅ Even with invalid token, still 200 because it's public
        mockMvc.perform(get("/posts")
                        .header("Authorization", "Bearer abc.def.ghi"))
                .andExpect(status().isOk());
    }
}
