package com.micro.ss.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.annotations.LogCheck;
import com.micro.ss.web.constants.AppConfig;
import com.micro.ss.web.enums.UserRelationEnum;
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
	
	@RequestMapping("follow")
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
	
	@RequestMapping("sendMsg")
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
		return ok(memberService.getMessageList(userId));
	}
	
	/**
	 * 获取最新的评论
	 */
	@RequestMapping("getCommentList")
	@ResponseBody
	public String getCommentaryList() {
		return ok(memberService.getRecentCommentary(appConfig.getHotCommentaryLimit()));
	}
}
