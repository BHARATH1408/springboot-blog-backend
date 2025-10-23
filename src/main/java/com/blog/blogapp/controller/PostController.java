package com.blog.blogapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.EntityUtils;
import com.blog.blogapp.exception.InvalidSortFieldException;
import com.blog.blogapp.model.Post;
import com.blog.blogapp.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    
	@Autowired
	private PostService postService;
	
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping
	public List <Post> getAllPosts(){
		return postService.getAllPosts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getByPostId(@PathVariable Long id){
		Optional<Post> post = postService.getByPostId(id);
		return post.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Post createNewPost(@Valid@RequestBody Post post){
		return postService.createNewPost(post);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid@RequestBody Post updatedPost) {
	    Post post = postService.updatePost(id, updatedPost);
	    return ResponseEntity.ok(post);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id){
		postService.deletePost(id);
		return ResponseEntity.noContent().build();
	}
	
	 @GetMapping("/search")
	    public Page<Post> searchPosts(
	        @RequestParam String keyword,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String order
	    ) {
		 /* // ✅ Validate sortBy
		    if (!EntityUtils.isValidField(Post.class, sortBy)) {
		        throw new InvalidSortFieldException("Invalid sort field: " + sortBy);
		    }*/
		 
		    String validSortField = EntityUtils.getValidFieldOrDefault(Post.class, sortBy, "id");
	        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(validSortField).ascending() : Sort.by(validSortField).descending();
	        return postService.searchPosts(keyword, PageRequest.of(page, size, sort));
	    }
	 
	  @GetMapping("/paged")
	    public Page<Post> getAllPosts(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size,
	            @RequestParam(defaultValue = "id") String sortBy,
	            @RequestParam(defaultValue = "asc") String order
	    ) {
		  
		   /* // ✅ Validate sortBy
		    if (!EntityUtils.isValidField(Post.class, sortBy)) {
		        throw new InvalidSortFieldException("Invalid sort field: " + sortBy);
		    }*/
		    String validSortField = EntityUtils.getValidFieldOrDefault(Post.class, sortBy, "id");
	        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(validSortField).ascending() : Sort.by(validSortField).descending();

	        return postService.getAllPosts(PageRequest.of(page, size, sort));
	    }
}
