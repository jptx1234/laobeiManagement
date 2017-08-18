package com.laobei.dao.service;

import org.apache.ibatis.annotations.Param;

import com.laobei.entity.UserLogin;

public interface LoginMapper {
	UserLogin findUser(@Param("username")String username,@Param("password")String password);
}
    