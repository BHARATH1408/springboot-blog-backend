package com.blog.blogapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.user.User;

public interface UserRepository extends JpaRepository<User,Long> {
        Optional<User> findByUsername(String Username);
        
        // Check if username already exists (for registration validation)
        boolean existsByUsername(String username);
}
