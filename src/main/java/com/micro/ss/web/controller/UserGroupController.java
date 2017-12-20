package com.micro.ss.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.annotations.LogCheck;
import com.micro.ss.web.data.model.Group;
import com.micro.ss.web.enums.ErrorMsgEnum;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.support.ControllerSupport;

/**
 * @author micro
 * @date 2017年7月11日
 * @description : 
 */
@RestController
@RequestMapping("userGroup")
public class UserGroupController extends ControllerSupport {

	@RequestMapping("add")
	@LogCheck
	public String createGroup(
			@RequestParam("name") String name,
			@RequestParam("image") String imageUrl) {
		Group group = new Group();
		group.setCreateTime(new Date());
		group.setCreatorId(curUserId());
		group.setImage(imageUrl);
		group.setGroupName(name);
		groupService.addGroup(group);
		return ok();
	}
	
	@RequestMapping("join")
	@LogCheck
	public String joinGroup(@RequestParam("groupId") Long groupId) {
		if (curUser() == null) {
			return fail(ErrorMsgEnum.NOT_LOGIN);
		}
		ServiceResult<Object> result = groupService.addUserToGroup(curUserId(), groupId);
		if (result.isSuccess()) {
			return ok();
		}
		return fail(result.getMsg());
	}
	
	/**
	 * 根据名称搜索小组
	 */
	@RequestMapping("searchGroup")
	public String searchGroup(@RequestParam("name") String name) {
		List<Group> groupList = groupService.likeSearchGroup(name);
		return ok(groupList);
	}
	
	/**
	 * 显示热门小组
	 */
}
