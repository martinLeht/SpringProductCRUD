package com.mithrandir.springcrud.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
	
	private final Logger theLogger = Logger.getLogger(getClass().getName());
	
	
	@Before("com.mithrandir.springcrud.aspect.AopExpressions.appFlow()")
	public void beforeLoggingAdvice(JoinPoint joinPoint) {
		
		// display called method and arguments
		String method = joinPoint.getSignature().toShortString();
		
		Object[] args = joinPoint.getArgs();
		
		theLogger.info("====> @Before from method: " + method);
		
		for (Object arg: args) {
			theLogger.info("====>> argument: " + arg);
		}
	}
	
	@AfterReturning(
			pointcut = "com.mithrandir.springcrud.aspect.AopExpressions.appFlow()",
			returning = "result")
	public void afterReturningLoggingAdvice(JoinPoint joinPoint, Object result) {
		
		// display called method and returned results		
		String method = joinPoint.getSignature().toShortString();
		
		
		
		theLogger.info("====> @AfterReturning from method: " + method);
		theLogger.info("====>> result: " + result);
		
	}
	

}
