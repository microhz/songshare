package com.micro.ss.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.Group;
import com.micro.ss.web.data.model.UserGroupRelation;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.service.GroupService;
import com.micro.ss.web.support.ServiceSupport;

/**
 * @author micro
 * @date 2017年7月11日
 * @description : 
 */
@Service
public class GroupServiceImpl extends ServiceSupport implements GroupService {

	public boolean addGroup(Group group) {
		groupDao.save(group);
		return true;
	}

	public ServiceResult<Object> addUserToGroup(Long userId, Long groupId) {
		Group group = groupDao.findOne(groupId);
		if (group == null) {
			return ServiceResult.getErrorResult("小组不存在");
		}
		UserGroupRelation userGroupRelation = new UserGroupRelation();
		userGroupRelation.setCreateTime(new Date());
		userGroupRelation.setGroupId(groupId);
		userGroupRelation.setStatus(StatusEnum.NORMAL.getStatus());
		userGroupRelation.setUserId(userId);
		userGroupRelationDao.save(userGroupRelation);
		return ServiceResult.getSuccess();
	}

	public List<Group> likeSearchGroup(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		return groupDao.getGroupLikeName(name);
	}

}
