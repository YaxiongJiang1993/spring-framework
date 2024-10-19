package com.davih.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestFactoryBean implements FactoryBean {

	private Class<?> mapperInterface;

	private SqlSession sqlSession;

	public TestFactoryBean(Class<?> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
		sqlSessionFactory.getConfiguration().addMapper(mapperInterface);
		this.sqlSession = sqlSessionFactory.openSession();
	}

	@Override
	public Object getObject() throws Exception {
		return sqlSession.getMapper(mapperInterface);
	}

	public Object getObject1() throws Exception {
		Object obj = Proxy.newProxyInstance(TestFactoryBean.class.getClassLoader(), new Class<?>[]{mapperInterface}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
		});
		return obj;
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}
