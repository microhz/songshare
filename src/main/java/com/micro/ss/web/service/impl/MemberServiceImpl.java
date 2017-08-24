package com.micro.ss.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.Message;
import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.UserHomeCommentary;
import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.data.model.UserRelation;
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
@Service
public class MemberServiceImpl extends ServiceSupport implements MemberService {

	public ServiceResult<Object> addRelation(Long userId, Long targetUserId, UserRelationEnum userRelationEnum) {
		if (userId != null && targetUserId != null && userRelationEnum != null) {
			if (userRelationDao.getUserRelationByUserIdAndTargetUserIdAndStatus(userId, targetUserId, userRelationEnum.getCode()) != null) {
				return ServiceResult.getErrorResult("重复关注");
			}
			UserRelation userRelation = new UserRelation();
			userRelation.setCreateTime(new Date());
			userRelation.setStatus(userRelationEnum.getCode());
			userRelation.setTargetUserId(targetUserId);
			userRelation.setUserId(userId);
			userRelation.setStatus(userRelationEnum.getCode());
			userRelationDao.save(userRelation);
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
			messageDao.save(message);
			return ServiceResult.getSuccess(null);
		}
		return ServiceResult.getErrorResult("参数错误");
	}

	public ServiceResult<List<UserHomeCommentary>> getHomeCommentary(Long userId) {
		List<UserHomeCommentary> userHomeCommentrayList = userHomeCommentaryDao.getUserHomeCommentaryByUserId(userId);
		if (userHomeCommentrayList != null && userHomeCommentrayList.size() > 0) {
			return ServiceResult.getSuccess(userHomeCommentrayList);
		}
		return ServiceResult.getSuccess(null);
	}

	public ServiceResult<List<MessageModel>> getMessageList(Long userId) {
		List<Message> messageList = messageDao.getByUserId(userId);
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
				List<UserInfo> userInfoList = userInfoDao.getByIdIn(userIdList);
				for (Message message : messageList) {
					for (UserInfo userInfo : userInfoList) {
						MessageModel messageModel = new MessageModel();
						messageModel.setUser(userInfo);
						if (userInfo.getId().equals(message.getSenderId())) {
							messageModel.setMessage(message);
						}
						messageModelList.add(messageModel);
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
		List<MusicCommentary> musicCommentarieList = musicCommentaryDao.getByRecentLimit(limitCount);
		if (musicCommentarieList != null && musicCommentarieList.size() > 0) {
			List<MusicCommentaryModel> musicCommentaryModelList = new ArrayList<MusicCommentaryModel>();
			List<Long> musicIdList = new ArrayList<Long>();
			for (MusicCommentary musicCommentary : musicCommentarieList) {
				if (musicCommentary.getTargetId() != null) {
					musicCommentarieList.add(musicCommentary);
					musicIdList.add(musicCommentary.getTargetId());// 获取id批量查询
				}
			}
			List<MusicInfo> musicInfoList = musicInfoDao.getByIdIn(musicIdList);
			for (MusicCommentaryModel musicCommentaryModel : musicCommentaryModelList) {
				musicCommentaryModel.setMusicInfo(getMusicInfoById(musicInfoList, musicCommentaryModel.getMusicCommentary().getTargetId()));
			}
			return ServiceResult.getSuccess(musicCommentaryModelList);
		}
		return ServiceResult.getSuccess(null);
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

	public ServiceResult<List<UserInfo>> getFans(Long userId) {
		if (userId != null) {
			List<UserRelation> userRelationList = userRelationDao.getByUserIdAndStatus(userId, UserRelationEnum.FOLLOW.getCode());
			if (userRelationList != null && userRelationList.size() > 0) {
				List<Long> userIdList = new ArrayList<Long>();
				for (UserRelation userRelation : userRelationList) {
					if (userRelation.getUserId() != null) {
						userIdList.add(userRelation.getUserId());
					}
				}
				if (userIdList.size() > 0) {
					return ServiceResult.getSuccess(userInfoDao.getByIdIn(userIdList));
				}
			}
		}
		return null;
	}


}
