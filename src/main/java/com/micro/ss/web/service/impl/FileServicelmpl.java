package com.micro.ss.web.service.impl;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.micro.ss.web.enums.FileTypeEnum;
import com.micro.ss.web.service.FileService;
import com.micro.ss.web.support.ServiceSupport;

/**
 * @author mapc 
 * @date 2017年7月2日
 */
@Service
public class FileServicelmpl extends ServiceSupport implements FileService {

	public String upload(InputStream inputStream, FileTypeEnum fileTypeEnum) {
		// TODO 上传到百度云
		return null;
	}

}
