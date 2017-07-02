package com.micro.ss.web.filter;


import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.DelegatingFilterProxy;

import com.micro.ss.web.constants.UserConstants;

/**
 * @author mapc 
 * @date 2017年7月2日
 */
public class LoginFilter extends DelegatingFilterProxy {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String url = httpServletRequest.getRequestURI();
			if (checkLogin(url)) {
				if (httpServletRequest.getSession().getAttribute(UserConstants.CURRENT_USER_KEY) != null) {
					((HttpServletResponse) response).sendRedirect("/ss/pages/index.html");
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean checkLogin(String url) {
		String excludeUrl = getFilterConfig().getInitParameter("exceludeUrl");
		String[] array = excludeUrl.split(",");
		for (String p : array) {
			Pattern pattern = Pattern.compile(p);
			if (pattern.matcher(url).find()) {
				return false;
			}
		}
		return true;
	}
	
	
}
