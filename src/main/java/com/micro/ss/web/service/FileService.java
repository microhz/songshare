package com.micro.ss.web.service;

import java.io.InputStream;

import com.micro.ss.web.enums.FileTypeEnum;

/**
 * @author mapc 
 * @date 2017年7月2日
 */
public interface FileService {

	/**
	 * 上传
	 */
	public String upload(InputStream inputStream, FileTypeEnum fileTypeEnum);
}
