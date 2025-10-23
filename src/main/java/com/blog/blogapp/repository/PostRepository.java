package com.blog.blogapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blogapp.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	    Page<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
	        String title, String content, Pageable pageable);
	
}
