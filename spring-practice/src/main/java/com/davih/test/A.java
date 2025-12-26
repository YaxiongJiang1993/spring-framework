package com.davih.test;

import com.davih.domain.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class A implements ApplicationContextAware {

	@Autowired
	private B b;

	@Value("lixunhuan")
	private Person person;

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void test(){
		applicationContext.publishEvent("A publish a event ... ");
		System.out.println("====A publish ====");
	}

	/*public A(B b) {
		this.b = b;
	}*/
}
