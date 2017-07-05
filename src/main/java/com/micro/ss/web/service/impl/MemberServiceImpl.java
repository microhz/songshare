package com.micro.ss.web.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.micro.ss.web.data.model.Message;
import com.micro.ss.web.data.model.UserRelation;
import com.micro.ss.web.data.model.UserRelationExample;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.enums.UserRelationEnum;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.service.MemberService;
import com.micro.ss.web.support.ServiceSupport;


/**
 * @author micro
 * @date 2017年7月5日
 * @description : 
 */
public class MemberServiceImpl extends ServiceSupport implements MemberService {

	public ServiceResult<Object> addRelation(Long userId, Long targetUserId, UserRelationEnum userRelationEnum) {
		if (userId != null && targetUserId != null && userRelationEnum != null) {
			UserRelationExample userRelationExample = new UserRelationExample();
			userRelationExample.or().andUserIdEqualTo(userId).andTargetUserIdEqualTo(targetUserId);
			if (userRelationMapper.countByExample(userRelationExample) > 0) {
				return ServiceResult.getErrorResult("重复关注");
			}
			UserRelation userRelation = new UserRelation();
			userRelation.setCreateTime(new Date());
			userRelation.setStatus(userRelationEnum.getCode());
			userRelation.setTargetUserId(targetUserId);
			userRelation.setUserId(userId);
			userRelation.setStatus(StatusEnum.NORMAL.getStatus());
			userRelationMapper.insert(userRelation);
			return ServiceResult.getSuccess(null);
		}
		return ServiceResult.getErrorResult("参数错误");
	}

	public ServiceResult<Object> sendMsg(Long userId, Long targetUserId, String msg) {
		if (userId != null && targetUserId != null && StringUtils.isNotBlank(msg)) {
			Message message = new Message();
			message.setMessage(msg);
			message.setSenderId(userId);
			message.setSendTime(new Date());
			message.setRecieveId(targetUserId);
			message.setStatus(StatusEnum.NORMAL.getStatus());
			messageMapper.insert(message);
			return ServiceResult.getSuccess(null);
		}
		return ServiceResult.getErrorResult("参数错误");
	}


}
