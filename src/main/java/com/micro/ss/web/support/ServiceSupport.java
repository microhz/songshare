package com.micro.ss.web.support;
/**
 * @author mapc 
 * @date 2017年7月1日
 */

import org.springframework.beans.factory.annotation.Autowired;

import com.micro.ss.web.data.mapper.UserInfoMapper;

public abstract class ServiceSupport {
	@Autowired
	protected UserInfoMapper userInfoMapper;
}
