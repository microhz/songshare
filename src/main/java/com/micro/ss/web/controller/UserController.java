package com.micro.ss.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.enums.ResponseInfoEnum;
import com.micro.ss.web.support.ControllerSupport;
import com.micro.ss.web.utils.MD5Utils;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
@RestController
@RequestMapping("user")
public class UserController extends ControllerSupport {

	/**
	 * 用户注册
	 * @return
	 */
	@RequestMapping("register")
	@ResponseBody
	public String reg(@RequestParam("name") String name,
			@RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(value = "age", required = false) Integer age,
			@RequestParam(value = "photo", required = false) String photo,
			@RequestParam(value = "portraitUrl" , required = false) String portraitUrl,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "wechat", required = false) String wechat,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "job", required = false) String job,
			@RequestParam(value = "qq", required = false) String qq,
			@RequestParam(value = "pageHome",required = false) String pageHome
			) {
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setProvinceId(provinceId);
		userInfo.setAge(age);
		userInfo.setPhoto(photo);
		userInfo.setPortraitUrl(portraitUrl);
		userInfo.setEmail(email);
		userInfo.setWechat(wechat);
		userInfo.setPassword(MD5Utils.md5(password));
		userInfo.setJob(job);
		userInfo.setQq(qq);
		userInfo.setPageHome(pageHome);
		if (userService.registerUser(userInfo)) {
			return ok();
		}
		return fail(ResponseInfoEnum.REGISTER_ERROR.getInfo());
	}
}
