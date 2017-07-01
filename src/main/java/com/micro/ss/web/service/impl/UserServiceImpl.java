package com.micro.ss.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.data.model.UserInfoExample;
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

	public UserInfo getUserInfoByName(String name, String password) {
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.or().andNameEqualTo(name).andPasswordEqualTo(password);
		List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
		return (userInfoList == null || userInfoList.size() == 0) ? null : userInfoList.get(0);
	}

	public UserInfo getUserInfoByEmail(String email, String password) {
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.or().andEmailEqualTo(email).andPasswordEqualTo(password);
		List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
		return (userInfoList == null || userInfoList.size() == 0) ? null : userInfoList.get(0);
	}

}
