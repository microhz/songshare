package com.micro.ss.web.enums;

/**
 * @author mapc
 * @date 2017年7月1日
 */
public enum ResponseInfoEnum {

	NOT_LOGIN("请登录"),
	PASSWORD_ERROR("密码错误"),
	USER_NOT_EXITS("用户不存在"),
	NAME_OR_PASSWORD_ERROR("用户名或密码错误"),
	REGISTER_ERROR("注册失败"),
	FORBIDEN("禁止操作"),
	UPDATE_ERROR("更新失败"),
	SYSTEM_ERROR("系统异常"),
	
	PARAM_ERROR("参数错误"),
	
	// ------
	MUSIC_NOT_EXITS("音乐不存在");
	
	private String info;

	private ResponseInfoEnum(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

}
