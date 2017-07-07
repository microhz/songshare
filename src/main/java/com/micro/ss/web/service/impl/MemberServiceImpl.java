package com.micro.ss.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.micro.ss.web.data.model.Message;
import com.micro.ss.web.data.model.MessageExample;
import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicCommentaryExample;
import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.MusicInfoExample;
import com.micro.ss.web.data.model.UserHomeCommentary;
import com.micro.ss.web.data.model.UserHomeCommentaryExample;
import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.data.model.UserInfoExample;
import com.micro.ss.web.data.model.UserRelation;
import com.micro.ss.web.data.model.UserRelationExample;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.enums.UserRelationEnum;
import com.micro.ss.web.pojo.MessageModel;
import com.micro.ss.web.pojo.MusicCommentaryModel;
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

	public ServiceResult<List<UserHomeCommentary>> getHomeCommentary(Long userId) {
		UserHomeCommentaryExample userHomeCommentaryExample = new UserHomeCommentaryExample();
		userHomeCommentaryExample.or().andUserIdEqualTo(userId);
		userHomeCommentaryExample.setOrderByClause("create_time DESC");
		List<UserHomeCommentary> userHomeCommentrayList = userHomeCommentaryMapper.selectByExample(userHomeCommentaryExample);
		if (userHomeCommentrayList != null && userHomeCommentrayList.size() > 0) {
			return ServiceResult.getSuccess(userHomeCommentrayList);
		}
		return ServiceResult.getSuccess(null);
	}

	public ServiceResult<List<MessageModel>> getMessageList(Long userId) {
		MessageExample messageExample = new MessageExample();
		messageExample.or().andRecieveIdEqualTo(userId);
		List<Message> messageList = messageMapper.selectByExample(messageExample);
		if (messageList != null && messageList.size() > 0) {
			List<MessageModel> messageModelList = new ArrayList<MessageModel>();
			List<Long> userIdList = new ArrayList<Long>();
			for (Message message : messageList) {
				if (message.getSenderId() == null) {
					getExceptionLogger().error("未知发送者,messageId -> " + message.getId());
				} else {
					MessageModel messageModel = new MessageModel();
					messageModel.setMessage(message);
					userIdList.add(message.getSenderId());
				}
			}
			if (userIdList.size() > 0) {
				UserInfoExample userInfoExample = new UserInfoExample();
				userInfoExample.or().andIdIn(userIdList);
				List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
				for (MessageModel messageModel : messageModelList) {
					for (UserInfo userInfo : userInfoList) {
						if (userInfo.getId().equals(messageModel.getMessage().getSenderId())) {
							messageModel.setUser(userInfo);
						}
					}
				}
			}
			return ServiceResult.getSuccess(messageModelList);
		}
		return ServiceResult.getSuccess(null);
	}

	public ServiceResult<List<MusicCommentaryModel>> getRecentCommentary(Integer limitCount) {
		if (limitCount == null) {
			return ServiceResult.getSuccess();
		}
		MusicCommentaryExample musicCommentaryExample = new MusicCommentaryExample();
		musicCommentaryExample.setStart(0);
		musicCommentaryExample.setEnd(limitCount);
		List<MusicCommentary> musicCommentarieList = musicCommentaryMapper.limitSelect(musicCommentaryExample);
		if (musicCommentarieList != null && musicCommentarieList.size() > 0) {
			List<MusicCommentaryModel> musicCommentaryModelList = new ArrayList<MusicCommentaryModel>();
			List<Long> musicIdList = new ArrayList<Long>();
			for (MusicCommentary musicCommentary : musicCommentarieList) {
				if (musicCommentary.getTargetId() != null) {
					musicCommentarieList.add(musicCommentary);
					musicIdList.add(musicCommentary.getTargetId());// 获取id批量查询
				}
			}
			MusicInfoExample musicInfoExample = new MusicInfoExample();
			musicInfoExample.or().andIdIn(musicIdList);
			List<MusicInfo> musicInfoList = musicInfoMapper.selectByExample(musicInfoExample);
			for (MusicCommentaryModel musicCommentaryModel : musicCommentaryModelList) {
				musicCommentaryModel.setMusicInfo(getMusicInfoById(musicInfoList, musicCommentaryModel.getMusicCommentary().getTargetId()));
			}
			return ServiceResult.getSuccess(musicCommentaryModelList);
		}
		return null;
	}
	
	private MusicInfo getMusicInfoById(List<MusicInfo> musicInfoList, Long musicId) {
		if (musicInfoList == null || musicInfoList.size() <= 0 || musicId == null) {
			return null;
		}
		for (MusicInfo musicInfo : musicInfoList) {
			if (musicInfo.equals(musicId)) {
				return musicInfo;
			}
		}
		return null;
	}


}
