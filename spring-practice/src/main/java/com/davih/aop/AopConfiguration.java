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

import com.davih.aop.advice.TestBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class for AOP.
 *
 * @author Yaxio
 */
@Configuration
@ComponentScan("com.davih.aop")
//@MapperScan("com.com.davih.cycle")
//@Import(DefaultAdvisorAutoProxyCreator.class)
@EnableAspectJAutoProxy
//@Import(AnnotationAwareAspectJAutoProxyCreator.class)
//@EnableAsync
//@EnableTransactionManagement
public class AopConfiguration {

	/**
	 * make a object to bean with proxy
	 */
	/*@Bean
	public ProxyFactoryBean accountService() {
		AccountService accountService = new AccountService();

		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.addAdvice(new TestBeforeAdvice());
		proxyFactoryBean.setTarget(accountService);

		return proxyFactoryBean;
	}*/

	/**
	 * proxy among the spring bean, use rules
	 * just use bean name as the rules
	 */
	/*@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
		BeanNameAutoProxyCreator beanNameAutoProxyCreator=new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setBeanNames("accountSe*");
		beanNameAutoProxyCreator.setInterceptorNames("testAroundAdvice");

		return beanNameAutoProxyCreator;
	}*/

	/**
	 * DefaultAdvisorAutoProxyCreator bean post processor
	 * //@Import(DefaultAdvisorAutoProxyCreator.class)
	 * @return
	 */
	/*@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
		// 强制cglib
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor(){
		NameMatchMethodPointcut pointcut=new NameMatchMethodPointcut();
		pointcut.addMethodName("test");

		DefaultPointcutAdvisor defaultPointcutAdvisor=new DefaultPointcutAdvisor();
		defaultPointcutAdvisor.setPointcut(pointcut);
		defaultPointcutAdvisor.setAdvice(new TestBeforeAdvice());

		return defaultPointcutAdvisor;
	}*/
}
