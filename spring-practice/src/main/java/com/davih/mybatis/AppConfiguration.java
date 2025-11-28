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

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.JdbcType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Configuration class for MyBatis integration.
 *
 * @author Yaxio
 */
@Configuration
@ComponentScan("com.davih.mybatis")
//@MapperScan("com.com.davih.cycle")
@TestMapperScan("com.davih.mybatis.mapper")
public class AppConfiguration {

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		// Add other configuration settings here

		// Environment
		Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource());
		configuration.setEnvironment(environment);

		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		return builder.build(configuration);
		/*SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();*/
	}

	@Bean
	public DataSource dataSource() {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/nacos";
		String dbUsername = "root";
		String dbPassword = "123456";
		String dbDriverClassName = "com.mysql.cj.jdbc.Driver";

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriverClassName);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;

	}
}
