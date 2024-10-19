package com.davih.cycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Yaxio
 */
public class CycleMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CycleConfiguration.class);
		AccountService accountService = context.getBean("accountService", AccountService.class);
		context.getBean(AccountService.class);
		System.out.println(accountService);
	}
}
