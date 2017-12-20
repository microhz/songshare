package com.micro.ss.web.service;

import java.util.List;

import com.micro.ss.web.data.model.Group;
import com.micro.ss.web.pojo.ServiceResult;

/**
 * @author micro
 * @date 2017年7月11日
 * @description :
 */
public interface GroupService {
	
	boolean addGroup(Group group);

	ServiceResult<Object> addUserToGroup(Long userId, Long groupId);
	
	List<Group> likeSearchGroup(String name);
}
