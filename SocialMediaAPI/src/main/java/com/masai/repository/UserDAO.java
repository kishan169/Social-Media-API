package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	public Optional<User> findByMobile(String mobile);
}
