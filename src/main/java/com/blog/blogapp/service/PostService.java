package com.blog.blogapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.model.Post;
import com.blog.blogapp.repository.PostRepository;

@Service
public class PostService {
    
	@Autowired
	private PostRepository postRepository;
	
	public Post createNewPost(Post post) {
		return postRepository.save(post);
	}
	
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	public Optional<Post> getByPostId(Long id){
		return postRepository.findById(id);
	}
	
	public Post updatePost(Long id,Post updatedPost){
		Post post =  postRepository.findById(id)
				          .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
		                  post.setTitle(updatedPost.getTitle());
		                  post.setContent(updatedPost.getContent());
		                  return postRepository.save(post);
			                 
		
	}
	
	public void deletePost(Long id) {
	    Post post = postRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
	    postRepository.delete(post);
	}
	
	public Page<Post> searchPosts(String keyword, Pageable pageable) {
		return postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, pageable);
	}
	
	 public Page<Post> getAllPosts(Pageable pageable) {
	        return postRepository.findAll(pageable);
	    }
}
