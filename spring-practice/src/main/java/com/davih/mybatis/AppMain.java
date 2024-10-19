package com.davih.mybatis;

import com.davih.mybatis.mapper.OrderMapper;
import com.davih.mybatis.mapper.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Yaxio
 */
public class AppMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfiguration.class);


		context.refresh();

		UserMapper userMapper = context.getBean(UserMapper.class);
		System.out.println(userMapper.getById());
		OrderMapper orderMapper = context.getBean(OrderMapper.class);
		System.out.println(orderMapper.getById());
		// sqlSession.selectOne() <-->sqlSessionTemplate.selectOne()
		//    -->sqlSessionProxy.selectOne()
		//       -->DefaultSqlSession.selectOne()
	}
}
