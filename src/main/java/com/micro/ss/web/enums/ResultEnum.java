package com.micro.ss.web.enums;

/**
 * @author mapc
 * @date 2017年7月1日
 */
public enum ResultEnum {
	SUCCESS(0, "请求成功"), ERROR(1, "系统异常"), LIMIT(2, "受到限制");
	private Integer code;
	private String msg;

	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
