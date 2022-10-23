package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Login;
import com.masai.model.SessionUser;
import com.masai.model.User;

import com.masai.repository.SessionDao;
import com.masai.repository.UserDAO;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private SessionDao sDao;
	
	@Autowired
	private UserDAO uDao;

	@Override
	public String loginToAccount(Login login) throws LoginException, UserException {
		
		
		Optional<User> optionalUser =  uDao.findByMobile(login.getMobile());
		
		if(!optionalUser.isPresent()) {
			throw new UserException("user Not Present in the List");
		}
		
		Optional<SessionUser> sUser =  sDao.findByUserId(optionalUser.get().getUserID());
		
		if(sUser.isPresent()) {
			throw new LoginException("User already Login with these mobile Number");
		}
		
		if(login.getMobile().equals(optionalUser.get().getMobile()) && login.getPassword().equals(optionalUser.get().getPassword())) {
			
			String key = RandomString.getRandomString();
			SessionUser sessionUser = new SessionUser(optionalUser.get().getUserID(), key, LocalDateTime.now());
			
			sDao.save(sessionUser);
			
			return sessionUser.toString();
		}else {
			throw new LoginException("Invalid Username And password");
		}	

	}

	@Override
	public String logoutFromAccount(String uniqueId) throws LoginException {
		List<SessionUser> allUser =  sDao.findAll();
		
		Optional<SessionUser> currUser =  sDao.findByUuid(uniqueId);
		
		if(!currUser.isPresent()) {
			throw new LoginException("You are not Login to the Account");
		}
		
		sDao.delete(currUser.get());
		List<SessionUser> allafterDelete =  sDao.findAll();
		
		if(allafterDelete.size()==allUser.size()) {
			throw new LoginException("Problem in Logout");
		}
		return "Successfully Logout";
	}
}
