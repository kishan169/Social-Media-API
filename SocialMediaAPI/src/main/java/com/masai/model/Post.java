package com.masai.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	private String description;
	
	private String title;
	
	private LocalDateTime postDate;
	
	
	@ElementCollection(targetClass = String.class)
	private Set<String> mediaList = new HashSet<>();
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable( name = "PostComment",
    joinColumns = @JoinColumn(name = "postId"), 
    inverseJoinColumns = @JoinColumn(name = "commentId"))
	private Set<Comment> comments = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable( name = "PostLike",
    joinColumns = @JoinColumn(name = "postId"), 
    inverseJoinColumns = @JoinColumn(name = "userId"))
	private Set<User> likes = new HashSet<>();
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}
	
	public Set<String> getMediaList() {
		return mediaList;
	}

	public void setMediaList(Set<String> mediaList) {
		this.mediaList = mediaList;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Integer postId, String description, String title, LocalDateTime postDate, Set<String> mediaList,
			User user, Set<Comment> comments, Set<User> likes) {
		super();
		this.postId = postId;
		this.description = description;
		this.title = title;
		this.postDate = postDate;
		this.mediaList = mediaList;
		this.user = user;
		this.comments = comments;
		this.likes = likes;
	}
	
	
	
}
