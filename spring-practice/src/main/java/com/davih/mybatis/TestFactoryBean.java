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

package com.davih.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.beans.factory.FactoryBean;

public class TestFactoryBean implements FactoryBean {

	private Class<?> mapperInterface;

	private SqlSession sqlSession;

	public TestFactoryBean(Class<?> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
		sqlSessionFactory.getConfiguration().addMapper(this.mapperInterface);
		this.sqlSession = sqlSessionFactory.openSession();
	}

	@Override
	public Object getObject() throws Exception {
		return this.sqlSession.getMapper(this.mapperInterface);
	}

	public Object getObject1() throws Exception {
		Object obj = Proxy.newProxyInstance(TestFactoryBean.class.getClassLoader(), new Class<?>[]{this.mapperInterface}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
		});
		return obj;
	}

	@Override
	public Class<?> getObjectType() {
		return this.mapperInterface;
	}
}
