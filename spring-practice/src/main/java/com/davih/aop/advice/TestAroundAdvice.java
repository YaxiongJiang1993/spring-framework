package com.davih.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class TestAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("TestAroundAdvice before ... ");
		Object proceed = invocation.proceed();
		System.out.println("TestAroundAdvice after ... ");
		return proceed;
	}
}
