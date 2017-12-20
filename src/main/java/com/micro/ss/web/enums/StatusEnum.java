package com.micro.ss.web.enums;

/**
 * @author mapc
 * @date 2017年7月2日
 */
public enum StatusEnum {

	NORMAL(0), DELETED(1);

	private Integer status;

	private StatusEnum(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
