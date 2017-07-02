package com.micro.ss.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.micro.ss.web.enums.ResponseInfoEnum;
import com.micro.ss.web.support.RestSupport;


/**
 * @author mapc 
 * @date 2017年7月1日
 * 捕获所有rest接口异常信息
 */
@Aspect
@Component
public class RestLogAspect extends RestSupport {
	
	private final static String LOG_PREFIXX = "API : ";

	@Pointcut("execution(* com.micro.ss.web.controller.*.*(..))")
	public void pointcut() {}
	
	@Around("pointcut()")
	public Object log(ProceedingJoinPoint proceedingJoinPoint) {
		Object[] args = proceedingJoinPoint.getArgs();
		String methodName = proceedingJoinPoint.getSignature().getName();
		String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
		StringBuilder sb = new StringBuilder();
		sb.append(LOG_PREFIXX).append("request to ").append(className).append(" - > ").append(methodName)
				.append(" - > ").append(" args : ");
		for (Object p : args) {
			sb.append(p).append(",");
		}
		String msg = sb.lastIndexOf(",") != -1 ? sb.substring(0, sb.lastIndexOf(",")) : sb.toString();
		getAPILogger().debug(msg);
		try {
			Object ret = proceedingJoinPoint.proceed();
			if (ret instanceof String) {
				getAPILogger().debug("response - > : " + ret);
			}
			return ret;
		} catch (Throwable e) {
			getExceptionLogger().error("execute controller error , msg : " + e.getMessage());
		}
		return fail(ResponseInfoEnum.SYSTEM_ERROR.getInfo());
	}
}
