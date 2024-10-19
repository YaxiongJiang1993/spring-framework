package com.davih.cotrs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Yaxio
 */
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		AccountService accountService = context.getBean("accountService", AccountService.class);
		context.getBean(AccountService.class);
		System.out.println(accountService);
	}
}
