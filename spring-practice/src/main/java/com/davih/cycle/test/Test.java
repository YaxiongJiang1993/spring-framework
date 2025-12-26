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

package com.davih.cycle.test;

import com.davih.cycle.CycleConfiguration;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

/**
 * Main application class.
 *
 * @author Yaxio
 */
public class Test {

	private Test() {
	}

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CycleConfiguration.class);
		A a = context.getBean("a", A.class);
		context.getBean(A.class);
		System.out.println(a);

		System.out.println("=====================");
		test3();
	}

	private static void test3() throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CycleConfiguration.class);

		Map<String, Object> systemEnvironment = context.getEnvironment().getSystemEnvironment();
		System.out.println(systemEnvironment);

		System.out.println("*****************************************");
		Map<String, Object> systemProperties = context.getEnvironment().getSystemProperties();
		System.out.println(systemProperties);

		System.out.println("*****************************************");
		MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
		System.out.println(propertySources);
	}

	private static void test2() throws IOException {
		ApplicationContext context = new AnnotationConfigApplicationContext();
		Resource resource = context.getResource("https://www.baidu.com/");
		System.out.println(resource.getFilename());
		System.out.println(resource.getURL());

		Resource resource2 = context.getResource("classpath:spring-module.md");
		System.out.println(resource2.getFilename());
		System.out.println(resource2.getURL());

		Resource resource3 = context.getResource("file:///home/david/test/c2.xml");
		System.out.println(resource3.getFilename());
		System.out.println(resource3.getURI());
	}

	private static void test1() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(C.class);

		beanFactory.registerBeanDefinition("a", beanDefinition);

		System.out.println(beanFactory.getBean("a"));
	}

	/**
	 * scan and register
	 */
	private static void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
		beanDefinitionReader.register(C.class);

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.scan("com.davih.cycle");

//		context.register(C.class);
		context.refresh();
		System.out.println(context.getBean(C.class));
		System.out.println(context.getBean(A.class));
	}
}
