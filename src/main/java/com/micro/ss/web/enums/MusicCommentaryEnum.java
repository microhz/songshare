package com.micro.ss.web.enums;

/**
 * @author mapc
 * @date 2017年7月2日
 */
public enum MusicCommentaryEnum {

	SIGLE_MUSIC(1, "单曲评论");
	private Integer typeCode;
	private String name;

	private MusicCommentaryEnum(Integer typeCode, String name) {
		this.typeCode = typeCode;
		this.name = name;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public String getName() {
		return name;
	}

}
