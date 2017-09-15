package com.laobei.service;

import java.util.List;

import com.laobei.entity.UserLogin;

public interface UserService {

	UserLogin login(String username, String password);

	List<UserLogin> listAll();

	void updateOrAddUser(UserLogin user);

}
