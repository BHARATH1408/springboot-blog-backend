package com.blog.blogapp.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException (String message) {
		super(message);
	}
}
