package com.micro.ss.web.enums;

/**
 * @author mapc
 * @date 2017年7月2日
 */
public enum MusicStatusEnum {
	NORMAL(0, "正常"), DELETED(1, "删除");

	private int code;
	private String status;

	private MusicStatusEnum(int code, String status) {
		this.code = code;
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

}
