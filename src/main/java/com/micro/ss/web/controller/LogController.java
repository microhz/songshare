package com.micro.ss.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.annotations.LogCheck;
import com.micro.ss.web.data.model.Log;
import com.micro.ss.web.enums.ErrorMsgEnum;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.support.ControllerSupport;

/**
 * @author micro
 * @date 2017年7月11日
 * @description : 
 */
@RestController
@RequestMapping("log")
public class LogController extends ControllerSupport {

	@RequestMapping("addLog.do")
	@LogCheck
	public String addLog(@RequestParam("content") String content,
			@RequestParam("title") String title) {
		logService.addLog(curUserId(), title, content);
		return ok();
	}
	
	@RequestMapping("getLog.do")
	public Object getLog(@RequestParam(value = "userId", required = false)Long userId) {
		if (userId == null) {
			userId = curUserId();
		}
		if (userId == null) {
			return fail("请登录");
		}
		ServiceResult<List<Log>> logResult = logService.getLogByUserId(userId);
		if (logResult.isFalse()) {
			return fail(logResult.getMsg());
		}
		return ok(logResult.getData());
	}
	
	
	/**
	 * 日志删除
	 */
	@RequestMapping("delLog.do")
	@LogCheck
	public String delLog(@RequestParam("logId") Long logId) {
		ServiceResult<Object> result = logService.delLog(logId);
		if (result.isFalse()) {
			return fail(result.getMsg());
		}
		return ok();
	}
	
	/**
	 * 重新编辑日志
	 */
	@RequestMapping("editLog.do")
	@LogCheck
	public String editLog(@RequestParam(value = "title", required = false) String title,
			@RequestParam("id") Long id,
			@RequestParam(value = "content", required = false) String content){
		ServiceResult<Log> result = logService.getLogById(id);
		if (result.isFalse()) {
			return fail(result.getMsg());
		}
		if (result.getData() == null) {
			return fail(ErrorMsgEnum.GROUP_NOT_EXITS);
		}
		Log log = result.getData();
		if (StringUtils.isNotBlank(title)) log.setTitle(title);
		if (StringUtils.isNotBlank(content)) log.setContent(content);
		ServiceResult<Object> updateResult = logService.updateLogById(log);
		if (updateResult.isFalse()) {
			return fail(updateResult.getMsg());
		}
		return ok();
	} 
	
}
