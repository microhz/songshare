package com.micro.ss.web.support;
/**
 * @author mapc 
 * @date 2017年7月1日
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.micro.ss.web.constants.UserConstants;
import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.service.FileService;
import com.micro.ss.web.service.MusicService;
import com.micro.ss.web.service.UserService;

public abstract class ControllerSupport extends RestSupport {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected MusicService musicService;
	
	@Autowired
	protected FileService fileService;
	
	@Autowired
	protected HttpSession httpSession;

	protected UserInfo curUser() {
		Object user = httpSession.getAttribute(UserConstants.CURRENT_USER_KEY);
		if (user == null) {
			return null;
		}
		return (UserInfo)user;
	}
	
	protected Long curUserId() {
		if (curUser() != null) {
			return curUser().getId();
		}
		return null;
	}

	
}
