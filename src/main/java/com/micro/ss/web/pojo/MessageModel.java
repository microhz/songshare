package com.micro.ss.web.pojo;
/**
 * @author micro
 * @date 2017年7月6日
 * @description : 
 */

import com.micro.ss.web.data.model.Message;
import com.micro.ss.web.data.model.UserInfo;

public class MessageModel {

	private UserInfo user;
	private Message message;

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
