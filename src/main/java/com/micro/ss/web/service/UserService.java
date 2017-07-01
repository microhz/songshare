package com.micro.ss.web.service;

import com.micro.ss.web.data.model.UserInfo;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
public interface UserService {

	boolean registerUser(UserInfo userInfo);
	
	UserInfo getUserInfoByName(String name, String password);
	
	UserInfo getUserInfoByEmail(String email, String password);
}
