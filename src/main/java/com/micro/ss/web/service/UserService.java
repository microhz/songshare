package com.micro.ss.web.service;

import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.pojo.ServiceResult;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
public interface UserService {

	boolean registerUser(UserInfo userInfo);
	
	UserInfo getUserInfoByName(String name, String password);
	
	UserInfo getUserInfoByEmail(String email, String password);
	
	boolean updateUserInfo(UserInfo userInfo);
	
	ServiceResult<UserInfo> getUserById(Long userId);
}
