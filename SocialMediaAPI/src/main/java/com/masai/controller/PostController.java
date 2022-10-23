package com.masai.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.PostException;
import com.masai.exception.UserException;
import com.masai.model.Post;
import com.masai.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService pService;
	
	@PostMapping("/users/{id}")
	public ResponseEntity<Post> createNewPost(@RequestBody Post post, @PathVariable("id") String userID) throws LoginException{
		Post p = pService.SavedNewPost(post, userID);
		return new ResponseEntity<Post>(p,HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{id}/{pid}")
	public ResponseEntity<Post> updatePost(@PathVariable("pid") Integer postId,@RequestBody Post post, @PathVariable("id") String uniqueId) throws LoginException, PostException{
		Post p = pService.updatePost(postId, post, uniqueId);
		return new ResponseEntity<Post>(p,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Post>> getAllPost(@PathVariable("id") String uniqueId) throws LoginException, PostException, UserException{
		List<Post> p = pService.getAllPost(uniqueId);
		return new ResponseEntity<List<Post>>(p,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/latest/{id}")
	public ResponseEntity<List<Post>> getLatestPost(@PathVariable("id") String uniqueId) throws LoginException, PostException, UserException{
		List<Post> p = pService.getAlllatestPost(uniqueId);
		return new ResponseEntity<List<Post>>(p,HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{id}/{pid}")
	public ResponseEntity<Post> getPostById(@PathVariable("pid") Integer postId , @PathVariable("id") String uniqueId) throws LoginException, PostException, UserException{
		Post p = pService.getPostById(postId, uniqueId);
		return new ResponseEntity<Post>(p,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{id}/{pid}")
	public ResponseEntity<String> deletePostById(@PathVariable("pid") Integer postId , @PathVariable("id") String uniqueId) throws LoginException, PostException, UserException{
		String str = pService.deletePost(postId, uniqueId);
		return new ResponseEntity<String>(str,HttpStatus.CREATED);
	}
}
