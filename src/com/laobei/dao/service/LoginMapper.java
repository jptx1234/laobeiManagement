package com.laobei.dao.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.laobei.entity.UserLogin;

public interface LoginMapper {
	UserLogin findUser(@Param("username")String username,@Param("password")String password);

	List<UserLogin> listAll();

	void saveUser(UserLogin user);

	void updateUser(UserLogin user);
}
    