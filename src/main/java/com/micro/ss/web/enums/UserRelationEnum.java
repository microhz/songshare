package com.micro.ss.web.enums;

/**
 * @author micro
 * @date 2017年7月5日
 * @description :
 */
public enum UserRelationEnum {

	FOLLOW(0, "关注"), BLACK(1, "屏蔽");

	private Integer code;

	private String name;

	private UserRelationEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
