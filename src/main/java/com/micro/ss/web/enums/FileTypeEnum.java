package com.micro.ss.web.enums;

/**
 * @author mapc
 * @date 2017年7月2日
 */
public enum FileTypeEnum {
	MUSIC(0, "音乐文件");
	private Integer code;
	private String type;

	private FileTypeEnum(Integer code, String type) {
		this.code = code;
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

}
