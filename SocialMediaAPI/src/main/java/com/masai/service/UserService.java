package com.masai.service;

import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;

import com.masai.exception.UserException;
import com.masai.model.User;

public interface UserService {
	

	public User savedNewUSer(User user);
	
	public List<User> findAllUser(String uniqueId) throws LoginException;
	
	public String followUsers(Integer userID,String uniqueId) throws LoginException, UserException;
	
	public String unfollowUser(Integer userId, String uniqueId) throws LoginException, UserException;
	
	public Set<User> followersByID(Integer userId, String uniqueId) throws LoginException, UserException;
	
	public Set<User> getMyFollower(String uniqueId) throws LoginException, UserException;
	
	public Set<User> getMyFollowingList(String uniqueId);
	
	public String blockUser(Integer userId,String uniqueId) throws LoginException, UserException;
	
	public Set<User> getAllBlockUser(String uniqueId) throws LoginException;
}
