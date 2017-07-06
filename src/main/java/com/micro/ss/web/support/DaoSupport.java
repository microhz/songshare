package com.micro.ss.web.support;
/**
 * @author mapc 
 * @date 2017年7月1日
 */

import org.springframework.beans.factory.annotation.Autowired;

import com.micro.ss.web.data.mapper.UserCollectionMapper;
import com.micro.ss.web.data.mapper.UserInfoMapper;

public abstract class DaoSupport {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserCollectionMapper userCollectionMapper;
	
}
