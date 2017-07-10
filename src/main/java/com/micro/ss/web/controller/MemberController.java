package com.micro.ss.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.annotations.LogCheck;
import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.enums.UserRelationEnum;
import com.micro.ss.web.pojo.MessageModel;
import com.micro.ss.web.pojo.MusicCommentaryModel;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.support.ControllerSupport;

/**
 * @author micro
 * @date 2017年7月5日
 * @description : 用户关系控制器
 */
@RestController
@RequestMapping("member")
public class MemberController extends ControllerSupport {
	
	@RequestMapping("follow.do")
	@ResponseBody
	@LogCheck
	public String follow(@RequestParam("targetUserId") Long targetUserId) {
		/*if (curUser() == null) {
			return fail(ErrorMsgEnum.NOT_LOGIN);
		}*/
		ServiceResult<Object> result = memberService.addRelation(curUserId(), targetUserId, UserRelationEnum.FOLLOW);
		if(result.isSuccess()){
			return ok();
		}
		return fail(result.getMsg());
	}
	
	@RequestMapping("sendMsg.do")
	@ResponseBody
	@LogCheck
	public String sendMsg(@RequestParam("msg") String msg, @RequestParam("targetUserId") Long targetUserId) {
		/*if (curUser() == null) {
			return fail(ErrorMsgEnum.NOT_LOGIN);
		}*/
		ServiceResult<Object> result = memberService.sendMsg(curUserId(), targetUserId, msg);
		if(result.isSuccess()) {
			return ok();
		}
		return fail(result.getMsg());
	}
	
	/**
	 * 获取其他用户发送的信息
	 */
	@RequestMapping("getMsgList")
	@ResponseBody
	@LogCheck
	public String getMsgList(@RequestParam(value = "userId", required = false) Long userId) {
		if (userId == null) {
			userId = curUserId();
		}
		ServiceResult<List<MessageModel>> result = memberService.getMessageList(userId);
		if (result.isSuccess()) {
			return ok(result.getData());
		}
		return fail(result.getMsg());
	}
	
	/**
	 * 获取最新的评论
	 */
	@RequestMapping("getCommentList.do")
	@ResponseBody
	public String getCommentaryList() {
		ServiceResult<List<MusicCommentaryModel>> result = memberService.getRecentCommentary(appConfig.getHotCommentaryLimit());
		if (result.isSuccess()) {
			return ok(result.getData());
		}
		return fail(result.getMsg());
	}
	
	/**
	 * 获取我的好友
	 */
	@RequestMapping("getFans.do")
	@ResponseBody
	@LogCheck
	public String getFollower() {
		ServiceResult<List<UserInfo>> result = memberService.getFans(curUserId());
		if (result.isSuccess()) {
			return ok(result.getData());
		}
		return fail(result.getMsg());
	}
}

