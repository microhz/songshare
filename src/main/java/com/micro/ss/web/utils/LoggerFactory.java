package com.micro.ss.web.utils;

import org.slf4j.Logger;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
public class LoggerFactory {
	

	public static Logger getMainLogger() {
		return org.slf4j.LoggerFactory.getLogger("main");
	}
	
	/*public static Logger getExceptionLogger() {
		
	}*/
}
