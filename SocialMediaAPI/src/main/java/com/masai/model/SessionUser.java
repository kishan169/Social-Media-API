package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SessionUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId;
	
	private Integer userId;
	
	private String uuid;
	
	private LocalDateTime time;

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}


	public SessionUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public SessionUser(Integer userId, String uuid, LocalDateTime time) {
		super();
		this.userId = userId;
		this.uuid = uuid;
		this.time = time;
	}

	@Override
	public String toString() {
		return "SessionUser [sessionId=" + sessionId + ", uuid=" + uuid + ", userId=" + userId + ", time=" + time + "]";
	}
	
	
	
}
