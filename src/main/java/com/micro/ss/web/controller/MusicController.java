package com.micro.ss.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.enums.ResponseInfoEnum;
import com.micro.ss.web.support.ControllerSupport;

/**
 * @author mapc 
 * @date 2017年7月2日
 */
@RestController
@RequestMapping("music")
public class MusicController extends ControllerSupport {

	@RequestMapping("getUploadList")
	@ResponseBody
	public String getCurUploadList() {
		if (curUser() != null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		return ok(musicService.getUploadMusic(curUserId()));
	}
}
