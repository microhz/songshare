package com.micro.ss.web.service;
/**
 * @author micro
 * @date 2017年7月11日
 * @description : 
 */

import java.util.List;

import com.micro.ss.web.data.model.Log;
import com.micro.ss.web.pojo.ServiceResult;

public interface LogService {
	ServiceResult<Object> addLog(Long userId, String title, String content);
	
	ServiceResult<Object> delLog(Long logId);
	
	ServiceResult<Object> updateLog(Long logId, String title, String content);
	
	ServiceResult<List<Log>> getLogByUserId(Long userId);
	
	ServiceResult<Object> updateLogById(Log log);
	
	ServiceResult<Log> getLogById(Long id);
}
