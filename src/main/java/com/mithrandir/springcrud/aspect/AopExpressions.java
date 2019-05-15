package com.mithrandir.springcrud.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
	
	@Pointcut("execution(* com.mithrandir.springcrud.dao.*.*(..))")
	public void forPackageDao() {}
	
	@Pointcut("execution(* com.mithrandir.springcrud.service.*.*(..))")
	public void forPackageService() {}
	
	@Pointcut("execution(* com.mithrandir.springcrud.controller.*.*(..))")
	public void forPackageController() {}
	
	// Combine pointcuts to make one pointcut for multiple packages
	@Pointcut("forPackageDao() || forPackageService() || forPackageController()")
	public void appFlow() {}
}
