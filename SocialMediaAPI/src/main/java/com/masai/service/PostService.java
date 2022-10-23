package com.masai.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.masai.exception.PostException;
import com.masai.exception.UserException;
import com.masai.model.Post;

public interface PostService {
	
	public Post SavedNewPost(Post post,String uniqueId) throws LoginException;
	
	public Post getPostById(Integer postId, String uniqueId) throws PostException, LoginException;
	
	public String deletePost(Integer postId, String uniqueId) throws PostException, LoginException;
	
	public Post updatePost(Integer postID, Post post, String uniqueId) throws PostException, LoginException;
	
	public List<Post> getAllPost(String uniqueId) throws UserException, LoginException;
	
	public List<Post> getAlllatestPost(String uniqueId) throws LoginException;

	
}
