package com.davih.listen;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Yaxio
 */
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfiguration.class);
		context.refresh();

		context.publishEvent("1234567");

		AccountService accountService = context.getBean("accountService", AccountService.class);
		context.getBean(AccountService.class);
		System.out.println(accountService);
	}
}
