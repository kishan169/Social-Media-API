package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.PostException;
import com.masai.exception.UserException;
import com.masai.model.Post;
import com.masai.model.SessionUser;
import com.masai.model.User;
import com.masai.repository.PostDao;
import com.masai.repository.SessionDao;
import com.masai.repository.UserDAO;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostDao pDao;
	
	@Autowired
	private UserDAO uDao;
	
	@Autowired
	private SessionDao sDao;

	@Override
	public Post SavedNewPost(Post post,String uniqueId) throws LoginException {
		
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		post.setPostDate(LocalDateTime.now());
		post.setUser(user);
		Post savedPost =  pDao.save(post);
		

		user.getPosts().add(savedPost);
		uDao.save(user);
		return savedPost;
	}

	@Override
	public Post getPostById(Integer postId, String uniqueId) throws PostException, LoginException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		
		Optional<Post> optionalPost =  pDao.findById(postId);
		
		if(!optionalPost.isPresent()) {
			throw new PostException("Not found any post with this Id");
		}
		return optionalPost.get();
	}

	@Override
	public String deletePost(Integer postId, String uniqueId) throws PostException, LoginException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		Optional<Post> post =  pDao.findById(postId);
		user.getPosts().remove(post.get());
		pDao.delete(post.get());
		uDao.save(user);
		return "Successfully Deleted";
	}

	@Override
	public Post updatePost(Integer postID, Post post,String uniqueId) throws PostException, LoginException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		
		Optional<Post> optionalPost =  pDao.findById(postID);
		
		if(!optionalPost.isPresent()) {
			throw new PostException("Not found any post with this Id");
		}
		User user =  optionalPost.get().getUser();
		post.setUser(optionalPost.get().getUser());
		return pDao.save(post);
	}

	@Override
	public List<Post> getAllPost(String uniqueId) throws UserException, LoginException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		return user.getPosts();
	}

	@Override
	public List<Post> getAlllatestPost(String uniqueId) throws LoginException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		
		List<Post> posts = pDao.getAllPostByDate();
		return posts;
	}
}
