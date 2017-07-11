package com.micro.ss.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping("addLog")
	@ResponseBody
	@LogCheck
	public String addLog(@RequestParam("content") String content,
			@RequestParam("title") String title) {
		logService.addLog(curUserId(), title, content);
		return ok();
	}
	
	
	/**
	 * 日志删除
	 */
	@RequestMapping("delLog")
	@ResponseBody
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
	@RequestMapping("editLog")
	@ResponseBody
	@LogCheck
	public String editLog(@RequestParam("title") String title,
			@RequestParam("id") Long id,
			@RequestParam("content") String content){
		ServiceResult<Log> result = logService.getLogById(id);
		if (result.isFalse()) {
			return fail(result.getMsg());
		}
		if (result.getData() == null) {
			return fail(ErrorMsgEnum.GROUP_NOT_EXITS);
		}
		Log log = result.getData();
		log.setTitle(title);
		log.setContent(content);
		ServiceResult<Object> updateResult = logService.updateLogById(log);
		if (updateResult.isFalse()) {
			return fail(updateResult.getMsg());
		}
		return ok();
	} 
	
}
