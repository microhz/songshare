package com.micro.ss.web.controller;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.annotations.UnLogCheck;
import com.micro.ss.web.constants.UserConstants;
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
	@UnLogCheck
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
	
	/**
	 * 用户登录
	 */
	@RequestMapping("login")
	@ResponseBody
	@UnLogCheck
	public String login(@RequestParam("username") String userNameOrEmail,
			@RequestParam("password") String password, HttpSession httpSession){
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail(userNameOrEmail);
		password = MD5Utils.md5(password);
		UserInfo user = userService.getUserInfoByName(userNameOrEmail, password);
		if (user == null) {
			userInfo.setName(userNameOrEmail);
			user = userService.getUserInfoByEmail(userNameOrEmail, password);
			if (user == null) {
				return fail(ResponseInfoEnum.NAME_OR_PASSWORD_ERROR.getInfo());
			}
		}
		httpSession.setAttribute(UserConstants.CURRENT_USER_KEY, user);
		return ok();
	}
	
	/**
	 * 用户登出
	 */
	@RequestMapping("logout")
	@ResponseBody
	public String logout() {
		if (curUser() == null) {
			return ok();
		}
		httpSession.removeAttribute(UserConstants.CURRENT_USER_KEY);
		return ok();
	}
	
	/**
	 * 用户信息的完善更新
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(value = "age", required = false) Integer age,
			@RequestParam(value = "photo", required = false) String photo,
			@RequestParam(value = "portraitUrl" , required = false) String portraitUrl,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "wechat", required = false) String wechat,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "job", required = false) String job,
			@RequestParam(value = "qq", required = false) String qq,
			@RequestParam(value = "pageHome",required = false) String pageHome
			) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.FORBIDEN.getInfo());
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setId(curUser().getId());
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
		if (userService.updateUserInfo(userInfo)) {
			return ok();
		}
		return fail(ResponseInfoEnum.UPDATE_ERROR.getInfo());
	}
	
}
