package com.micro.ss.web.service;

import com.micro.ss.web.enums.UserRelationEnum;
import com.micro.ss.web.pojo.ServiceResult;

/**
 * @author micro
 * @date 2017年7月5日
 * @description : 
 */
public interface MemberService {
	/**
	 * 用户关系建立
	 * @param userId
	 * @param targetUserId
	 * @return
	 */
	ServiceResult<Object> addRelation(Long userId, Long targetUserId, UserRelationEnum userRelationEnum);
	
	/**
	 * 用户之间发送信息
	 */
	ServiceResult<Object> sendMsg(Long userId, Long targetUserId, String msg);
}
