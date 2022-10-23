package com.masai.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userID;
	
	@NotBlank
	@NotNull
	@Size(min = 2, max = 15,message = "size should be between 2 and 15")
	private String userName;
	
	@NotBlank
	@NotNull
	@Size(min=10,max=10,message="Mobile number should be in 10 digits")
	private String mobile;
	
	@NotBlank
	@NotNull
	@Size(min=8,max=18,message="Password should be 8 to 18 digits")
	private String password;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable( name = "UserPost",
    joinColumns = @JoinColumn(name = "userId"), 
    inverseJoinColumns = @JoinColumn(name = "postId"))
	private List<Post> posts = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable( name = "User_Followers",
    joinColumns = @JoinColumn(name = "userId"), 
    inverseJoinColumns = @JoinColumn(name = "followerId"))
	private Set<User> followers = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable( name = "User_Followings",
    joinColumns = @JoinColumn(name = "userId"), 
    inverseJoinColumns = @JoinColumn(name = "followingId"))
	private Set<User> followings = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable( name = "User_BlokedLists",
    joinColumns = @JoinColumn(name = "userId"), 
    inverseJoinColumns = @JoinColumn(name = "blockUserId"))
	private Set<User> blockUsers = new HashSet<>();
	

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<User> getBlockUsers() {
		return blockUsers;
	}

	public void setBlockUsers(Set<User> blockUsers) {
		this.blockUsers = blockUsers;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public User(Integer userID, String userName, String mobile) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.mobile = mobile;
	}

	public Set<User> getFollowings() {
		return followings;
	}

	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}
	
	
	
	
}
