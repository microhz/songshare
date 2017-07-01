package com.micro.ss.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.micro.ss.web.annotations.UnLogCheck;
import com.micro.ss.web.enums.ResponseInfoEnum;

/**
 * @author mapc 
 * @date 2017年7月1日
 * 登录校验拦截器
 */
@Component
public class LogCheckIntercepter implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			System.out.println("登录校验 : " + handlerMethod.getMethod().getName());
			if (handlerMethod.getMethodAnnotation(UnLogCheck.class) != null) {
				return true;
			}
		}
		response.sendError(HttpStatus.FORBIDDEN.value(), ResponseInfoEnum.NOT_LOGIN.getInfo());
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
