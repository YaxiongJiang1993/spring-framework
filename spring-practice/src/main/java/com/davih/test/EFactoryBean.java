package com.davih.test;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EFactoryBean implements FactoryBean {

	@Override
	public Object getObject() throws Exception {
		return new E();
	}

	@Override
	public Class<?> getObjectType() {
		return E.class;
	}
}
