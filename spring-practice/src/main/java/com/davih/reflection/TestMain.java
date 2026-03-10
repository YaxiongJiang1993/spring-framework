package com.davih.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// 1. 获取 Class 对象
		Class<?> clazz = Class.forName("com.davih.reflection.TestService");

		// 所有字段
		for (Field f : clazz.getDeclaredFields()) {
			System.out.println("字段：" + f.getName());
		}

		// 所有方法
		for (Method m : clazz.getDeclaredMethods()) {
			System.out.println("方法：" + m.getName());
			System.out.println("方法 getParameterTypes：" + m.getParameterTypes());
		}

		Method method=clazz.getMethod("setAge", new Class[]{Integer.class});
		Parameter[] parameters = method.getParameters();
		for (Parameter parameter: parameters){
			System.out.println("parameter name: "+parameter.getName());
			System.out.println("parameter type: "+parameter.getType());
		}
	}
}
