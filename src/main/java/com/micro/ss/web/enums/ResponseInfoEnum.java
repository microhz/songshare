package com.micro.ss.web.enums;

/**
 * @author mapc
 * @date 2017年7月1日
 */
public enum ResponseInfoEnum {

	NOT_LOGIN("请登录"),
	PASSWORD_ERROR("密码错误"),
	USER_NOT_EXITS("用户不存在"),
	REGISTER_ERROR("注册失败");
	private String info;

	private ResponseInfoEnum(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

}
