package com.masai.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.UserException;
import com.masai.model.User;
import com.masai.service.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	private UserService uService;

	@PostMapping("/user/new")
	public ResponseEntity<User> createNewUser(@RequestBody User user){
		User u1 = uService.savedNewUSer(user);
		return new ResponseEntity<User>(u1,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/find/{id}")
	public ResponseEntity<List<User>> findListofUser(@PathVariable("id") String uniqueId) throws LoginException{
		List<User> users = uService.findAllUser(uniqueId);
		List<User> sortedUsers = users.stream()
									.map(user -> new User(user.getUserID(), user.getUserName(), user.getMobile()))
									.collect(Collectors.toList());
		return new ResponseEntity<List<User>>(sortedUsers,HttpStatus.OK);
	}
	
	@GetMapping("/user/followers/{id}/{uid}")
	public ResponseEntity<Set<User>> findFollowersofUser(@PathVariable("uid") Integer userId,@PathVariable("id") String uniqueId) throws LoginException, UserException{
		Set<User> users = uService.followersByID(userId, uniqueId);
		return new ResponseEntity<Set<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/user/myfollowers/{id}")
	public ResponseEntity<Set<User>> findMyFollowers(@PathVariable("id") String uniqueId) throws LoginException, UserException{
		Set<User> users = uService.getMyFollower(uniqueId);
		return new ResponseEntity<Set<User>>(users,HttpStatus.OK);
	}
	
	@PutMapping("/users/follow/{id}/{uid}")
	public ResponseEntity<String> followUserById(@PathVariable("uid") Integer userId, @PathVariable("id") String uniqueId) throws LoginException, UserException{
		String str = uService.followUsers(userId, uniqueId);
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	@PutMapping("/users/unfollow/{id}/{uid}")
	public ResponseEntity<String> unfollowUserById(@PathVariable("uid") Integer userId, @PathVariable("id") String uniqueId) throws LoginException, UserException{
		String message = uService.unfollowUser(userId, uniqueId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/user/blockList/{id}")
	public ResponseEntity<Set<User>> getAllBlockedUser(@PathVariable("id") String uniqueId) throws LoginException{
		Set<User> blockList = uService.getAllBlockUser(uniqueId);
		return new ResponseEntity<Set<User>>(blockList,HttpStatus.OK);
	}
	
	@PutMapping("/user/block/{id}/{uid}")
	public ResponseEntity<String> blockUser(@PathVariable("uid") Integer userId, @PathVariable("id") String uniqueId) throws LoginException, UserException{
		String message =  uService.blockUser(userId, uniqueId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
