package com.laobei.entity;

import java.io.Serializable;

/**
 * 
 * @author 梁璐
 *	
 */
public class UserLogin implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Long uid;

	private String username;
	
	private String password;
	
	private int isAdmin = 0;
	
	private int status = 1;
	
	

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
