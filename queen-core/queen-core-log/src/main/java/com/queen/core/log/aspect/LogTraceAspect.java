package com.queen.core.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * 为异步方法添加traceId
 *
 * @author jensen
 */
@Aspect
public class LogTraceAspect {

	@Pointcut("@annotation(org.springframework.scheduling.annotation.Async)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		LogTraceUtil.insert();
		Object result = point.proceed();
		LogTraceUtil.remove();
		return result;
	}
}
