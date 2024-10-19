package com.davih.aop;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.*;

/**
 * @author Yaxio
 */
@Configuration
@ComponentScan("com.davih.aop")
//@MapperScan("com.com.davih.cycle")
//@Import(DefaultAdvisorAutoProxyCreator.class)
@EnableAspectJAutoProxy
//@Import(AnnotationAwareAspectJAutoProxyCreator.class)
public class AopConfiguration {

	/*@Bean
	public ProxyFactoryBean accountService() {
		AccountService accountService = new AccountService();

		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.addAdvice(new TestBeforeAdvice());
		proxyFactoryBean.setTarget(accountService);

		return proxyFactoryBean;
	}*/

	/*@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
		BeanNameAutoProxyCreator beanNameAutoProxyCreator=new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setBeanNames("accountSe*");
		beanNameAutoProxyCreator.setInterceptorNames("testAroundAdvice");

		return beanNameAutoProxyCreator;
	}*/

	/*@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();

		return defaultAdvisorAutoProxyCreator;
	}*/

//	@Bean
//	public DefaultPointcutAdvisor defaultPointcutAdvisor(){
//		NameMatchMethodPointcut pointcut=new NameMatchMethodPointcut();
//		pointcut.addMethodName("test");
//
//		DefaultPointcutAdvisor defaultPointcutAdvisor=new DefaultPointcutAdvisor();
//		defaultPointcutAdvisor.setPointcut(pointcut);
//		defaultPointcutAdvisor.setAdvice(new TestBeforeAdvice());
//
//		return defaultPointcutAdvisor;
//	}
}
