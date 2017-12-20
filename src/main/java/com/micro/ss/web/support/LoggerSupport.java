package com.micro.ss.web.support;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

/**
 * @author mapc
 * @date 2017年7月1日
 */
public abstract class LoggerSupport {
	
	private static Map<String, Logger> loggerCacheMap = new HashMap<String, Logger>();
	
	protected Logger getMainLogger() {
		return getLoggerByName("main");
	}
	
	private Logger getLoggerByName(String name) {
		return loggerCacheMap.get(name) == null ? org.slf4j.LoggerFactory.getLogger(name) : loggerCacheMap.get(name);
	}
	
	protected Logger getExceptionLogger() {
		return getLoggerByName("exception");
	}
	
	protected Logger getAPILogger() {
		return getLoggerByName("api");
	}
	static {
		Logger mainLogger = org.slf4j.LoggerFactory.getLogger("main");
		loggerCacheMap.put("main", mainLogger);
		Logger apiLogger = org.slf4j.LoggerFactory.getLogger("api");
		loggerCacheMap.put("api", apiLogger);
		Logger exceptionLogger = org.slf4j.LoggerFactory.getLogger("exception");
		loggerCacheMap.put("exception", exceptionLogger);
		
//		Logger sqlLogger = org.slf4j.LoggerFactory.getLogger("com.apache.mybatis");
//		loggerCacheMap.put("sql", sqlLogger);
	}
}
