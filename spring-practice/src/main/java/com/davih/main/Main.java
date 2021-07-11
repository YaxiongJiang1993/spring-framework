/*
 * Copyright 2002-2012 the original author or authors.
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

package com.davih.main;

import com.davih.configuration.BaseConfiguration;
import com.davih.domain.Person;
import com.davih.service.UserService;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Yaxio
 */
public final class Main {

	private Main() {

	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				BaseConfiguration.class);
		System.out.println("===========================================");
		Person person = context.getBean("person", Person.class);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		System.out.println(person);
		UserService userService = context.getBean(UserService.class);
		System.out.println(userService);
		userService.sayHello();
	}

	private static void closeCircularReferences() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BaseConfiguration.class);
		// 关闭循环依赖
		AbstractAutowireCapableBeanFactory beanFactory = (AbstractAutowireCapableBeanFactory) context.getBeanFactory();
		beanFactory.setAllowCircularReferences(false);
		context.refresh();
	}
}
