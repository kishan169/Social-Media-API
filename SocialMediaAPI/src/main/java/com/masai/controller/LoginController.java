package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.Login;
import com.masai.service.LoginService;


@RestController
public class LoginController {
	
	@Autowired
	private LoginService lService;
	
	@PostMapping("/user/login")
	private ResponseEntity<String> logIntoAccount(@RequestBody Login login) throws LoginException, UserException{
		String str = lService.loginToAccount(login);
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	@DeleteMapping("/user/logout/{id}")
	private ResponseEntity<String> logoutfromAccount(@PathVariable("id") String uniqueId) throws LoginException, UserException{
		String str = lService.logoutFromAccount(uniqueId);
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
}
