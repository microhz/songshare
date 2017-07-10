package com.micro.ss.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.support.ControllerSupport;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
@RestController
@RequestMapping("test")
public class TestController extends ControllerSupport {

	@RequestMapping("login.do")
	@ResponseBody
	public String doLogin() {
		return ok("你好.ss");
	}
	
}
