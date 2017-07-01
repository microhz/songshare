package com.micro.ss.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.micro.ss.web.annotations.UnLogCheck;
import com.micro.ss.web.enums.ResponseInfoEnum;
import com.micro.ss.web.support.LoggerSupport;

/**
 * @author mapc 
 * @date 2017年7月1日
 * 登录校验拦截器
 */
@Component
public class LogCheckIntercepter extends LoggerSupport implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			if (handlerMethod.getMethodAnnotation(UnLogCheck.class) != null) {
				return true;
			}
		}
		getMainLogger().error("reject forbiden request : " + request.getRequestURI());
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
