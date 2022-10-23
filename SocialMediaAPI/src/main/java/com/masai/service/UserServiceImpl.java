package com.masai.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserException;
import com.masai.model.SessionUser;
import com.masai.model.User;
import com.masai.repository.SessionDao;
import com.masai.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO uDao;
	
	@Autowired
	private SessionDao sDao;

	@Override
	public User savedNewUSer(User user) {
		User u1 =  uDao.save(user);
		return u1;
	}

	@Override
	public List<User> findAllUser(String uniqueId) throws LoginException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		
		List<User> use = uDao.findAll();
		return use;
	}

	@Override
	public String followUsers(Integer userID, String uniqueId) throws LoginException, UserException {
		
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
			
		Optional<User> searchedUser = uDao.findById(userID);
		
		if(user.getBlockUsers().contains(searchedUser.get())) {
			throw new UserException("User is in your block List");
		}
		
		user.getFollowers().add(searchedUser.get());
		uDao.save(user);
		
		return "followed";
	}

	@Override
	public Set<User> followersByID(Integer userId,String uniqueId) throws LoginException, UserException {
		
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		Optional<User> searcheduser = uDao.findById(userId);
		
		if(!searcheduser.isPresent()) {
			throw new UserException("Not found any user with given Id");
		}
		Set<User> list = searcheduser.get().getFollowers();
		return list;
	}

	@Override
	public Set<User> getMyFollower(String uniqueId) throws LoginException, UserException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		
		if(user.getFollowers().size()==0) {
			throw new UserException("Not found any Followers");
		}
		return user.getFollowers();
	}

	@Override
	public String blockUser(Integer userId, String uniqueId) throws LoginException, UserException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		Optional<User> userOptional = uDao.findById(userId);
		if(!userOptional.isPresent()) {
			throw new UserException("Not found any user with these Id");
		}
		
		Set<User> currFollowers =  user.getFollowers();
		
		if(currFollowers.contains(userOptional.get())) {
			currFollowers.remove(userOptional.get());
		}
		
		uDao.save(user);
		
		return "Blocked User Successfully!";
	}

	@Override
	public String unfollowUser(Integer userId, String uniqueId) throws LoginException, UserException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		Optional<User> searchedUser = uDao.findById(userId);
		
		if(!user.getFollowers().contains(searchedUser.get())) {
			throw new UserException("User is not in you following List");
		}
		user.getFollowers().remove(searchedUser.get());
		uDao.save(user);
		return "Unfollowed Successfully!";
	}

	@Override
	public Set<User> getAllBlockUser(String uniqueId) throws LoginException {
		Optional<SessionUser> sessionUser =  sDao.findByUuid(uniqueId);
		
		if(!sessionUser.isPresent()) {
			throw new LoginException("You have to Login First!");
		}
		Optional<User> currUser = uDao.findById(sessionUser.get().getUserId());
		
		User user = currUser.get();
		return user.getBlockUsers();
	}

	@Override
	public Set<User> getMyFollowingList(String uniqueId) {
		
		return null;
	}
	
	
}
