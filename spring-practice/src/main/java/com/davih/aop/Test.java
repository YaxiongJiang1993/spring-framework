/*
 * Copyright 2002-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.davih.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.davih.aop.advice.TestBeforeAdvice;

import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	private Test() {
	}

	public static void main(String[] args) {

//		testCglib();
//		testJdkProxy();
//		testProxyFactory2();

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfiguration.class);
		/*AccountService accountService = (AccountService) context.getBean("accountService");
		accountService.test();*/

		IAccount accountService = (IAccount) context.getBean("IAccountImpl");
		accountService.xxx();
//		context.registerBeanDefinition();
	}

	private static void testProxyFactory2() {

		IUserService target = new IUserService();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);
//		proxyFactory.addAdvice(new TestBeforeAdvice());

		proxyFactory.addAdvisor(new PointcutAdvisor() {
			@Override
			public Pointcut getPointcut() {
				return new StaticMethodMatcherPointcut() {
					@Override
					public boolean matches(Method method, Class<?> targetClass) {
						return method.getName().equals("test");
					}
				};
			}

			@Override
			public Advice getAdvice() {
				return new TestBeforeAdvice();
			}

			@Override
			public boolean isPerInstance() {
				return false;
			}
		});

		IUserService user = (IUserService) proxyFactory.getProxy();
		user.a();
	}

	private static void testProxyFactory() {

		IUserService target = new IUserService();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);
		proxyFactory.addAdvice(new MethodBeforeAdvice() {

			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println(" ... before ... ");
			}
		});

		IUserService user = (IUserService) proxyFactory.getProxy();
		user.test();
	}

	private static void testJdkProxy() {

		IUserService target = new IUserService();

		IUser user = (IUser) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{IUser.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(" before ... ");
				method.invoke(target, args);
				return null;
			}
		});

		user.test();
	}


	private static void testCglib() {

		AccountService target = new AccountService();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(AccountService.class);

		enhancer.setCallbacks(new Callback[]{
				new MethodInterceptor() {
					/**
					 * Intercept method invocation.
					 * @param o proxy object
					 * @param method method
					 * @param objects method params
					 * @param methodProxy proxy method
					 * @return result
					 * @throws Throwable exception
					 */
					@Override
					public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
						System.out.println(" ...........before.............  ");
//						Object result = methodProxy.invoke(target, objects);
//						Object result=method.invoke(target, objects);
						Object result = methodProxy.invokeSuper(o, objects);
//						Object result=method.invoke(o, objects);
						System.out.println(" ...........after.............  ");
						return result;
					}
				},
				NoOp.INSTANCE
		});

		enhancer.setCallbackFilter(new CallbackFilter() {
			@Override
			public int accept(Method method) {
				if (method.getName().equals("test")) {
					return 0;
				}
				else {
					return 1;
				}
			}
		});

		AccountService accountService = (AccountService) enhancer.create();
		accountService.a();
	}
}
