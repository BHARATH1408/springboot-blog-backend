package com.blog.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.JwtUtil;
import com.blog.blogapp.repository.UserRepository;
import com.blog.blogapp.user.User;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	 @PostMapping("/register")
	 @Operation(summary = "Register a new user")
	 public ResponseEntity<String> register(@RequestBody User user) {
		 if (userRepository.existsByUsername(user.getUsername())) {
		        return ResponseEntity.badRequest().body("Username already exists!");
		    }
	        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
	        userRepository.save(user);
	        return ResponseEntity.ok("User registered!");
	    }
	 
	  @PostMapping("/login")
	  @Operation(summary = "Login and get JWT Token")
	  public ResponseEntity<String> login(@RequestBody User loginRequest) {
	        User user = userRepository.findByUsername(loginRequest.getUsername())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        if (new BCryptPasswordEncoder().matches(loginRequest.getPassword(), user.getPassword())) {
	            String token = jwtUtil.generateToken(user.getUsername());
	            return ResponseEntity.ok(token);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    }
}
