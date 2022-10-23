package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.SessionUser;

@Repository
public interface SessionDao extends JpaRepository<SessionUser, Integer>{
	
	public Optional<SessionUser> findByUserId(Integer userId);
	
	public Optional<SessionUser> findByUuid(String uniqueId);
}
