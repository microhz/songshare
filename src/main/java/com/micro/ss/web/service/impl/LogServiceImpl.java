package com.micro.ss.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.Log;
import com.micro.ss.web.data.model.LogExample;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.service.LogService;
import com.micro.ss.web.support.ServiceSupport;

/**
 * @author micro
 * @date 2017年7月11日
 * @description : 
 */
@Service
public class LogServiceImpl extends ServiceSupport implements LogService {

	public ServiceResult<Object> addLog(Long userId, String title, String content) {
		Log log = new Log();
		log.setContent(content);
		log.setCreateTime(new Date());
		log.setUserId(userId);
		log.setTitle(title);
		log.setStatus(StatusEnum.NORMAL.getStatus());
		logMapper.insert(log);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<Object> delLog(Long logId) {
		if (logMapper.selectByPrimaryKey(logId) == null) {
			return ServiceResult.getErrorResult("日志不存在");
		}
		logMapper.deleteByPrimaryKey(logId);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<Object> updateLog(Long logId, String title, String content) {
		Log log = logMapper.selectByPrimaryKey(logId);
		if (log == null) {
			return ServiceResult.getErrorResult("日志不存在");
		}
		log.setTitle(title);
		log.setContent(content);
		logMapper.updateByPrimaryKey(log);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<List<Log>> getLogByUserId(Long userId) {
		LogExample logExample = new LogExample();
		logExample.or().andUserIdEqualTo(userId);
		List<Log> logList = logMapper.selectByExample(logExample);
		return ServiceResult.getSuccess(logList);
	}

	public ServiceResult<Object> updateLogById(Log log) {
		logMapper.updateByPrimaryKey(log);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<Log> getLogById(Long id) {
		Log log = logMapper.selectByPrimaryKey(id);
		return ServiceResult.getSuccess(log);
	}

}
