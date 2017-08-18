package com.laobei.service;

import com.laobei.entity.UserLogin;

public interface UserService {

	UserLogin login(String username, String password);

}
