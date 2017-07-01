package com.micro.ss.web.utils;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
public class MD5Utils {

	public static String md5(String content) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			return Base64.getEncoder().encodeToString(messageDigest.digest(content.getBytes()));
		} catch (Exception ex) {
			// log
		}
		return "";
	}
	
}
