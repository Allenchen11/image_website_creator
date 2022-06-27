package com.iwc.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CallDaoLoggingAspect {
	
	@Autowired
	private HttpServletRequest request;

	private static final Logger logger = LogManager.getLogger(CallDaoLoggingAspect.class);

	@Before("execution(* com.iwc.dao.*.*(..))")
	public void before(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		logger.info(String.format("%s.%s(%s)", target.getClass().getName(), methodName, Arrays.toString(args)));
		
		String clientIp = request.getRemoteAddr();
		logger.info(clientIp);
		if("0:0:0:0:0:0:0:1".equals(clientIp)) {
			logger.warn(clientIp+" is black ip,stop");
			Thread.currentThread().interrupt();
		}
		
	}
}
