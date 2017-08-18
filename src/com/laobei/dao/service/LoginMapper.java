package com.laobei.dao.service;

import com.laobei.eneity.UserLogin;

public interface LoginMapper {
	UserLogin findUser(String username,String password);
}
