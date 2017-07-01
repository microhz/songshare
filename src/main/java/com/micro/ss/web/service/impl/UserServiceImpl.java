package com.micro.ss.web.service.impl;

import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.service.UserService;
import com.micro.ss.web.support.ServiceSupport;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
@Service
public class UserServiceImpl extends ServiceSupport implements UserService {

	public boolean registerUser(UserInfo userInfo) {
		try {
			userInfoMapper.insert(userInfo);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

}
