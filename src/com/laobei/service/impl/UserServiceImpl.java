package com.laobei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.laobei.dao.service.LoginMapper;
import com.laobei.entity.UserLogin;
import com.laobei.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private LoginMapper loginMapper;
	
	@Override
	public UserLogin login(String username, String password) {
		if (username == null || password == null) {
			return null;
		}
		return loginMapper.findUser(username, password);
	}

}
