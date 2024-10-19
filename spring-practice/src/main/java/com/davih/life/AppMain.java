package com.davih.life;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Yaxio
 */
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfiguration.class);
		context.refresh();

		AccountService accountService = context.getBean("accountService", AccountService.class);
		context.getBean(AccountService.class);
		System.out.println(accountService);
	}
}
