package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer>{
	
	@Query("select p from Post p ORDER BY p.postDate DESC ")
	public List<Post> getAllPostByDate();
}
