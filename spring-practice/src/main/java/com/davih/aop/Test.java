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

/**
 * Spring AOP 核心术语提取
 * Aspect (切面)：表示切面，比如被 @Aspect 注解的类就是切面，可以在切面中去定义 Pointcut、Advice 等等。
 *
 * Join point (连接点)：表示连接点，表示一个程序在执行过程中的一个点，比如一个方法的执行，比如一个异常的处理。在 Spring AOP 中，
 * 一个连接点通常表示一个方法的执行。
 *
 * Advice (通知)：表示通知，表示在一个特定连接点上所采取的动作。Advice 分为不同的类型，后面详细讨论。在很多 AOP 框架中，包括 Spring，
 * 会用 Interceptor 拦截器来实现 Advice，并且在连接点周围维护一个 Interceptor 链。
 *
 * Pointcut (切点)：表示切点，用来匹配一个或多个连接点。Advice 与切点表达式是关联在一起的，Advice 将会执行在和切点表达式所匹配的连接
 * 点上。
 *
 * Introduction (引入)：可以使用 @DeclareParents 来给所匹配的类添加一个接口，并指定一个默认实现。
 *
 * Target object (目标对象)：目标对象，被代理对象。
 *
 * AOP proxy (AOP 代理)：表示代理工厂，用来创建代理对象的。在 Spring Framework 中，要么是 JDK 动态代理，要么是 CGLIB 代理。
 *
 * Weaving (织入)：表示织入，表示创建代理对象的动作。这个动作可以发生在编译时期（比如 Aspectj），或者运行时，比如 Spring AOP。
 */
public class Test {

	private Test() {
	}

	public static void main(String[] args) {

//		testCglib();
//		System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//		System.out.println("user.dir = " + System.getProperty("user.dir"));
//		testJdkProxy();
//		testProxyFactory();
//		testProxyFactory2();


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfiguration.class);
		AccountService accountService = (AccountService) context.getBean("accountService");
		accountService.test();
		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfiguration.class);
		IAccount accountService = (IAccount) context.getBean("accountService");
		accountService.xxx();*/
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
//		user.a();
		user.test();
	}

	private static void testProxyFactory() {

		IUserService target = new IUserService();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);

//		proxyFactory.setInterfaces(IUser.class);
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
		// vm options: -Dcglib.debugLocation=/home/david/moon/spring-framework/spring-practice/build/classes/java/main
		// add proxy class to disk, not only memory

		AccountService target = new AccountService();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(AccountService.class);

		enhancer.setCallbacks(new Callback[]{
				new MethodInterceptor() {
					/**
					 * Intercept method invocation.
					 * @param o proxy object 就是这里的accountService
					 * @param method method
					 * @param objects method params
					 * @param methodProxy proxy method
					 * @return result
					 * @throws Throwable exception
					 */
					@Override
					public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
						// methodProxy proxied two method in this class, test(),CGLIB$test$1()
						// methodProxy.invoke() test()
						// methodProxy.invokeSuper() CGLIB$test$1()
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
			// 返回Callbacks数组索引
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
		accountService.test();
	}
}
