package com.masai.service;

import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Login;

public interface LoginService {
	public String loginToAccount(Login login) throws LoginException, UserException;
	
	public String logoutFromAccount(String uniqueId) throws LoginException;
}
